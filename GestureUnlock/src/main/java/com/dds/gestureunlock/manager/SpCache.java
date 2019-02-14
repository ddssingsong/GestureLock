package com.dds.gestureunlock.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by dds on 2019/2/13.
 * android_shuai@163.com
 */
public class SpCache implements ICache {

    private final static String SP_GESTURE_UNLOCK = "sp_gesture_unlock";
    private final static String SP_KEY_GESTURE_UNLOCK_CODE = "gesture_unlock_code";
    private final static String SP_KEY_GESTURE_UNLOCK_CODE_GUEST = "gesture_unlock_code_guest";

    @Override
    public boolean isGestureCodeSet(Context context) {
        return !TextUtils.isEmpty(getGestureCodeSet(context));

    }

    @Override
    public String getGestureCodeSet(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_UNLOCK, Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_GESTURE_UNLOCK_CODE, null);
    }

    @Override
    public String getGuestGestureCodeSet(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_UNLOCK, Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_GESTURE_UNLOCK_CODE_GUEST, null);
    }

    @Override
    public void clearGestureCode(Context context) {
        setGestureCode(context, "");
    }

    @Override
    public void setGestureCode(Context context, String gestureCode) {
        SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_UNLOCK, Context.MODE_PRIVATE);
        sp.edit().putString(SP_KEY_GESTURE_UNLOCK_CODE, gestureCode).apply();
    }

    @Override
    public void setGuestGestureCode(Context context, String gestureCode) {
        SharedPreferences sp = context.getSharedPreferences(SP_GESTURE_UNLOCK, Context.MODE_PRIVATE);
        sp.edit().putString(SP_KEY_GESTURE_UNLOCK_CODE_GUEST, gestureCode).apply();
    }
}
