package com.vinson.mmanager.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.view.TextureView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.socks.library.KLog;
import com.vinson.mmanager.App;
import com.vinson.mmanager.R;
import com.vinson.mmanager.base.BaseActivity;
import com.vinson.mmanager.model.lift.LiftInfo;
import com.vinson.mmanager.model.ws.WSEvent;
import com.vinson.mmanager.services.WSService;
import com.vinson.mmanager.tools.Config;
import com.vinson.mmanager.ui.item.LiftDetailActivity;
import com.vinson.mmanager.utils.Constants;

import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.constants.ZegoPublisherState;
import im.zego.zegoexpress.constants.ZegoRoomState;
import im.zego.zegoexpress.constants.ZegoUpdateType;
import im.zego.zegoexpress.entity.ZegoCanvas;
import im.zego.zegoexpress.entity.ZegoStream;
import im.zego.zegoexpress.entity.ZegoUser;

@Route(path = Constants.AROUTE_PAGE_VIDEO_ROOM)
public class VideoRoomActivity extends BaseActivity {

    public static final int RR_REMOTE_PUSH_STREAM_SUCCESS = 1;
    public static final int RR_REMOTE_STOP_STREAM_SUCCESS = 2;

    public static final int MSG_REQUEST_PUSH_STREAM = 10;
    public static final int MSG_REQUEST_STOP_STREAM = 11;

    LiftInfo mLiftInfo;
    IZegoEventHandler mEventHandler = new IZegoEventHandler() {
        @Override
        public void onRoomStateUpdate(String roomID, ZegoRoomState state, int errorCode,
                                      JSONObject extendedData) {
            super.onRoomStateUpdate(roomID, state, errorCode, extendedData);
            KLog.d(roomID + "," + state);
        }

        @Override
        public void onRoomStreamUpdate(String roomID, ZegoUpdateType updateType,
                                       ArrayList<ZegoStream> streamList) {
            super.onRoomStreamUpdate(roomID, updateType, streamList);
            KLog.d(roomID + ", " + updateType + ", " + streamList.toString());

        }

        @Override
        public void onRoomUserUpdate(String roomID, ZegoUpdateType updateType,
                                     ArrayList<ZegoUser> userList) {
            super.onRoomUserUpdate(roomID, updateType, userList);
            KLog.d(roomID + ", " + updateType + "," + userList.toString());
        }

        @Override
        public void onPublisherStateUpdate(String streamID, ZegoPublisherState state,
                                           int errorCode, JSONObject extendedData) {
            super.onPublisherStateUpdate(streamID, state, errorCode, extendedData);
            KLog.d(streamID + ", " + state + ", " + errorCode);
        }
    };
    MyResultReceiver mResultReceiver;
    String localStreamId;
    String roomId;
    ZegoUser mZegoUser;
    String remoteStreamId;
    private TextureView mLocalRender, mRemoteRender;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResultReceiver = new MyResultReceiver(mHandler);
        String str = getIntent().getStringExtra(LiftDetailActivity.EXTRA_LIFT_INFO);
        mLiftInfo = mGson.fromJson(str, LiftInfo.class);

        localStreamId = Constants.PREFIX_USER + Config.getUserInfo().ID;
        roomId = Constants.PREFIX_LIFT + mLiftInfo.ID;
        mZegoUser = new ZegoUser(localStreamId);
        remoteStreamId = roomId;
    }

    @Override
    protected boolean handleMessage(Message message) {
        WSEvent event = new WSEvent();
        switch (message.what) {
            case MSG_REQUEST_PUSH_STREAM:
                event.target = new String[]{remoteStreamId};
                event.what = Constants.WS_REQUEST_PUSH_STREAM;
                event.data = localStreamId;
                WSService.requestStream(this, mResultReceiver, mGson.toJson(event));
                break;
            case MSG_REQUEST_STOP_STREAM:
                event.target = new String[]{remoteStreamId};
                event.what = Constants.WS_REQUEST_STOP_PUSH_STREAM;
                event.data = localStreamId;
                WSService.requestStopStream(this, mResultReceiver, mGson.toJson(event));
                break;
            default:
                KLog.d("known message :" + message.what);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_video_room;
    }

    @Override
    protected void initView() {
        super.initView();
        mLocalRender = findViewById(R.id.tv_local);
        mRemoteRender = findViewById(R.id.tv_remote);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onResume() {
        super.onResume();
        App.getEngine().setEventHandler(null);
        App.getEngine().setEventHandler(mEventHandler);
        // notify smart device to push stream
        mHandler.sendEmptyMessage(MSG_REQUEST_PUSH_STREAM);

        //1. CREATE USER
        //2. LOGIN ROOM
        App.getEngine().loginRoom(roomId, mZegoUser);
        //3. PUSH STREAM
        App.getEngine().startPublishingStream(localStreamId);
        //4. PULL STREAM
        App.getEngine().startPlayingStream(localStreamId, new ZegoCanvas(mLocalRender));
        // PULL REMOTE STREAM
        App.getEngine().startPlayingStream(remoteStreamId, new ZegoCanvas(mRemoteRender));
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.getEngine().logoutRoom(roomId);
        App.getEngine().stopPublishingStream();
        App.getEngine().stopPlayingStream(localStreamId);
        App.getEngine().stopPlayingStream(remoteStreamId);
        App.getEngine().setEventHandler(null);
        mHandler.sendEmptyMessage(MSG_REQUEST_STOP_STREAM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class MyResultReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            String data = resultData.getString(WSService.EXTRA_BUNDLE_STRING);
            switch (resultCode) {
                case RR_REMOTE_PUSH_STREAM_SUCCESS:
                    KLog.d("remote push steam success, " + data);
                    Toasty.success(VideoRoomActivity.this, "remote push steam success").show();
                    break;
                case RR_REMOTE_STOP_STREAM_SUCCESS:
                    KLog.d("remote stop steam success, " + data);
                    Toasty.success(VideoRoomActivity.this, "remote push steam success").show();
                    break;
                default:
                    KLog.d("known message :" + resultCode);
            }
        }
    }
}
