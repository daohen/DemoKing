package com.demo.king.base;

import android.app.Application;

import com.demo.king.di.component.AppComponent;
import com.demo.king.di.component.DaggerAppComponent;
import com.demo.king.di.component.Demo3Component;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:31
 */
public class App extends Application {

    private AppComponent appComponent;

    private Demo3Component demo3Component;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public Demo3Component createDemo3Component(){
        if (demo3Component == null)
            demo3Component = appComponent.plusDemo3Component();
        return demo3Component;
    }


}
