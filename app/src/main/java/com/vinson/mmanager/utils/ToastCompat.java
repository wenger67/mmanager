package com.vinson.mmanager.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.lang.reflect.Field;

public class ToastCompat {
    private static Field sField_TN;
    private static Field sField_TN_Handler;

    static {
        try {
            //noinspection JavaReflectionMemberAccess
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception ignored) {
        }
    }

    private static void hook(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new SafelyHandlerWrapper(preHandler));
        } catch (Exception ignored) {
        }
    }

    public static void showToast(Context context, CharSequence cs) {
        Toast toast = Toast.makeText(context, cs, Toast.LENGTH_LONG);
        hook(toast);
        toast.show();
    }

    public static class SafelyHandlerWrapper extends Handler {
        private Handler impl;

        public SafelyHandlerWrapper(Handler impl) {
            this.impl = impl;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception ignored) {
            }
        }

        @Override
        public void handleMessage(Message msg) {
            impl.handleMessage(msg);
        }
    }
}
