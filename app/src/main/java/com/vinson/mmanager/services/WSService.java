package com.vinson.mmanager.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.vinson.mmanager.App;
import com.vinson.mmanager.model.ws.WSEvent;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.ui.VideoRoomActivity;
import com.vinson.mmanager.utils.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WSService extends Service {
    public static final String TAG = WSService.class.getSimpleName();
    public static final String EXTRA_RESULT_RECEIVER = "extra.result.receiver";
    public static final String EXTRA_WS_EVENT = "extra.ws.event";
    public static final int COMMAND_INVALID = -1;
    public static final int COMMAND_REQUEST_PUSH_STREAM = 101;
    public static final int COMMAND_REQUEST_STOP_PUSH_STREAM = 102;
    public static final String EXTRA_START_COMMAND = "extra.start.command";
    public static final String EXTRA_BUNDLE_STRING = "extra.bundle.string";
    private static final int REQUEST_RETRY_DELAY = 30 * 1000;
    private static final int MSG_RETRY_RECORD = 1;
    private static final int MSG_LOAD_AUTHORITY = 3;
    private static final int MSG_LOAD_CONFIG = 4;
    private static final int MSG_OPEN_WEBSOCKET = 5;
    private static final int MSG_UPLOAD_DUMP_FILES = 6;
    private static final int MSG_UPLOAD_LOGS = 7;
    private static final int MSG_SCHEDULE_TASKS = 8;
    private static final int MSG_START_PUSH_STREAM = 101;
    private static final int WS_CLOSE_CODE = 1000;
    ResultReceiver resultReceiver;
    private ExecutorService mThreadPool;
    private ConnectivityManager mConnectivityManager;
    private boolean mNetworkAvailable;
    private boolean mStopped = true;
    private WebSocket mWebSocket;
    private Gson mGson;
    private String pushStreamRequester;

    private Handler mHandler = new Handler(msg -> {
        if (mStopped) {
            Log.w(TAG, "DataLoader stopped, ignore msg:" + msg.what);
            return true;
        }
        switch (msg.what) {
            case MSG_RETRY_RECORD:
//                retryRecord();
                break;
            case MSG_LOAD_AUTHORITY:
//                loadAuthority();
                break;
            case MSG_LOAD_CONFIG:
//                loadConfig();
                break;
            case MSG_OPEN_WEBSOCKET:
                if (mThreadPool != null && !mThreadPool.isShutdown())
                    mThreadPool.execute(this::openWebSocket);
                break;
            case MSG_UPLOAD_DUMP_FILES:
                if (mThreadPool != null && !mThreadPool.isShutdown())
//                    mThreadPool.execute(this::uploadDumpFiles);
                    break;
            case MSG_UPLOAD_LOGS:
                if (mThreadPool != null && !mThreadPool.isShutdown())
//                    mThreadPool.execute(this::uploadLogs);
                    break;
            case MSG_SCHEDULE_TASKS:
//                timerTask();
                break;
            case MSG_START_PUSH_STREAM:
                // TODO push stream
                startPushStream();
        }
        return true;
    });
    private ConnectivityManager.NetworkCallback mNetworkCallback =
            new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    Log.w(TAG, "network available");
                    mNetworkAvailable = true;
                    App.getInstance().showToast("网络已连接");
                    start();
                }

                @Override
                public void onLost(Network network) {
                    Log.w(TAG, "network lost");
                    mNetworkAvailable = false;
                    App.getInstance().showToast("网络已断开");
                    stop();
                }
            };

    public static void requestStream(Context context, ResultReceiver resultReceiver, String event) {
        Intent intent = new Intent(context, WSService.class);
        intent.putExtra(EXTRA_START_COMMAND, COMMAND_REQUEST_PUSH_STREAM);
        intent.putExtra(EXTRA_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(EXTRA_WS_EVENT, event);
        context.startService(intent);
    }

    public static void requestStopStream(Context context, ResultReceiver resultReceiver, String event) {
        Intent intent = new Intent(context, WSService.class);
        intent.putExtra(EXTRA_START_COMMAND, COMMAND_REQUEST_STOP_PUSH_STREAM);
        intent.putExtra(EXTRA_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(EXTRA_WS_EVENT, event);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mThreadPool = Executors.newFixedThreadPool(5);
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert mConnectivityManager != null;
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        mNetworkAvailable = (networkInfo != null && networkInfo.isConnected());
        mConnectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(),
                mNetworkCallback);
        mGson = new Gson();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mThreadPool.shutdown();
        mThreadPool = null;
        mConnectivityManager.unregisterNetworkCallback(mNetworkCallback);
    }

    private void start() {
        mStopped = false;
        // 1. load device config
        // 2. open websocket
        mHandler.sendEmptyMessage(MSG_OPEN_WEBSOCKET);
        // 3. upload records that record when disconnect or upload faield
        // 4. schedule tasks
    }

    private void stop() {
        mStopped = true;
        mHandler.removeCallbacksAndMessages(null);
        // report device offline
        // close websocket
    }

    private void closeWebSocket() {
        Log.d(TAG, "close websocket");
        if (mWebSocket != null) {
            mWebSocket.close(WS_CLOSE_CODE, null);
            mWebSocket = null;
            Log.d(TAG, "websocket closed successfully");
        } else {
            Log.d(TAG, "websocket already closed");
        }
    }

    private void openWebSocket() {
        Log.d(TAG, "openWebSocket");
        if (!mNetworkAvailable) {
            Log.w(TAG, "network unavailable when open websocket");
            return;
        }

        if (mWebSocket != null) {
            Log.w(TAG, "websocket already opened, close it first");
            closeWebSocket();
        }

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.SECONDS).build();
        Request request =
                new Request.Builder().url(Constants.WS_BASE_URL + Constants.WS_PATH).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                Log.d(TAG, "websocket onOpen");
                mWebSocket = webSocket;
                Log.d(TAG, "report device info through websocket");
                WSEvent event = new WSEvent();
                event.target = new String[]{"server"};
                event.what = "report";
                event.data = "USER_" + Config.getUserInfo().ID;
                mWebSocket.send(mGson.toJson(event));
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Log.d(TAG, "onMessage(text):" + text);
                WSEvent event = mGson.fromJson(text, WSEvent.class);
                switch (event.what) {
                    case "dev.upload.dump": {
                        mHandler.sendEmptyMessage(MSG_UPLOAD_DUMP_FILES);
                        break;
                    }
                    case "dev.upload.log": {
                        mHandler.removeMessages(MSG_UPLOAD_LOGS);
                        mHandler.sendEmptyMessage(MSG_UPLOAD_LOGS);
                        break;
                    }
                    case "upgrade": {
                        mHandler.removeCallbacksAndMessages(null);
//                        mListener.onUpgrade(event.data);
                        break;
                    }
                    case "push.stream":
                        pushStreamRequester = event.data;
                        mHandler.removeMessages(MSG_START_PUSH_STREAM);
                        mHandler.sendEmptyMessage(MSG_START_PUSH_STREAM);
                        break;
                    case Constants.WS_REMOTE_PUSH_STREAM_SUCCESS:
                        if (resultReceiver != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString(EXTRA_BUNDLE_STRING, event.data);
                            resultReceiver.send(VideoRoomActivity.RR_REMOTE_PUSH_STREAM_SUCCESS,
                                    bundle);
                        }
                        break;
                    case Constants.WS_REMOTE_STOP_STREAM_SUCCESS:
                        if (resultReceiver != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString(EXTRA_BUNDLE_STRING, event.data);
                            resultReceiver.send(VideoRoomActivity.RR_REMOTE_STOP_STREAM_SUCCESS,
                                    bundle);
                        }
                        break;
                    default:
                        Log.w(TAG, "unsupported event");
                        break;
                }
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                Log.d(TAG, "websocket onClosed:" + code + "," + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                Log.w(TAG, "websocket onFailure:" + t.getMessage());
                if (mStopped) {
                    Log.w(TAG, "DataLoader stopped, ignore websocket failure.");
                    return;
                }
                Log.w(TAG, "retry open websocket 10s later");
                mHandler.removeMessages(MSG_OPEN_WEBSOCKET);
                mHandler.sendEmptyMessageDelayed(MSG_OPEN_WEBSOCKET, 10 * 1000);
            }
        });
        client.dispatcher().executorService().shutdown();
    }

    void startPushStream() {
        WSEvent event = new WSEvent();
        event.target = new String[]{pushStreamRequester};
        event.what = "push.stream.start";
        event.data = "roomid:";
        mWebSocket.send(mGson.toJson(event));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        KLog.d();
        if (intent == null) {
            throw new IllegalStateException("Must start the service with intent");
        }

        switch (intent.getIntExtra(EXTRA_START_COMMAND, COMMAND_INVALID)) {
            case COMMAND_REQUEST_PUSH_STREAM:
                handleRequestStream(intent);
                break;
            case COMMAND_REQUEST_STOP_PUSH_STREAM:
                handleRequestStopStream(intent);
                break;
            case COMMAND_INVALID:
            default:
                super.onStartCommand(intent, flags, startId);
        }
        return START_NOT_STICKY;
    }

    private void handleRequestStream(Intent intent) {
        resultReceiver = intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
        String wsStr = intent.getStringExtra(EXTRA_WS_EVENT);
        if (wsStr != null) {
            mWebSocket.send(wsStr);
        } else KLog.w("ws event is null");

    }

    private void handleRequestStopStream(Intent intent) {
        resultReceiver = intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
        String wsStr = intent.getStringExtra(EXTRA_WS_EVENT);
        if (wsStr != null) {
            mWebSocket.send(wsStr);
        } else KLog.w("ws event is null");

    }
}
