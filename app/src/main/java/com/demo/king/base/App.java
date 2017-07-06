package com.demo.king.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/06 14:19
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
