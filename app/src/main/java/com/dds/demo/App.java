package com.dds.demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by dds on 2019/2/13.
 * android_shuai@163.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupLeakCanary();
    }

    //初始化LeakCanary
    protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
}
