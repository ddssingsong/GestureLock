package com.dds.demo;

import android.content.Context;
import android.text.TextUtils;

import com.dds.gestureunlock.manager.ICache;
import com.dds.gestureunlock.manager.SpCache;
import com.dds.gestureunlock.util.ResourceUtil;

/**
 * 手势密码入口
 */
public class GestureUnlock {
    private static GestureUnlock sGestureUnlock;
    private ICache cache;

    public static GestureUnlock getInstance() {
        if (sGestureUnlock == null) {
            sGestureUnlock = new GestureUnlock();
        }
        return sGestureUnlock;
    }

    public void init(Context applicationContext) {
        ResourceUtil.init(applicationContext);
        cache = new SpCache();
    }

    public void init(Context applicationContext, ICache cache) {
        ResourceUtil.init(applicationContext);
        this.cache = cache;
    }

    public void createGestureUnlock(Context activityContext) {
        GestureUnlockActivity.openActivity(activityContext, GestureUnlockActivity.TYPE_GESTURE_CREATE);
    }

    public void verifyGestureUnlock(Context activityContext) {
        GestureUnlockActivity.openActivity(activityContext, GestureUnlockActivity.TYPE_GESTURE_VERIFY);
    }

    public void modifyGestureUnlock(Context activityContext) {
        GestureUnlockActivity.openActivity(activityContext, GestureUnlockActivity.TYPE_GESTURE_MODIFY);
    }


    public boolean isGestureCodeSet(Context context) {
        return !TextUtils.isEmpty(getGestureCodeSet(context));
    }


    public String getGestureCodeSet(Context context) {
        return cache.getGestureCodeSet(context);
    }


    public void clearGestureCode(Context context) {
        cache.clearGestureCode(context);
    }


    public void setGestureCode(Context context, String gestureCode) {
        cache.setGestureCode(context, gestureCode);
    }
}
