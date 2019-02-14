package com.dds.gestureunlock.manager;

import android.content.Context;

/**
 * Created by dds on 2019/2/13.
 * android_shuai@163.com
 */
public interface ICache {


    // 判断是否设置了手势密码
    boolean isGestureCodeSet(Context context);

    // 获取手势密码
    String getGestureCodeSet(Context context);

    // 清空手势密码
    void clearGestureCode(Context context);

    // 设置手势密码
    void setGestureCode(Context context, String gestureCode);

}
