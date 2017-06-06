package com.demo.king.base;

import android.app.Application;

import com.demo.king.bean.User;
import com.demo.king.di.component.AppComponent;
import com.demo.king.di.component.DaggerAppComponent;
import com.demo.king.di.component.Demo3Component;
import com.demo.king.di.component.UserComponent;
import com.demo.king.di.module.AppModule;
import com.demo.king.di.module.UserModule;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:31
 */
public class App extends Application {

    private AppComponent appComponent;

    private Demo3Component demo3Component;

    private UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public Demo3Component createDemo3Component(){
        return demo3Component = appComponent.plusDemo3Component();
    }

    public Demo3Component getDemo3Component(){
        return demo3Component;
    }

    public void releaseDemo3Component(){
        demo3Component = null;
    }

    public UserComponent createUserComponent(User userA, User userB){
        return userComponent = appComponent.plusUserComponent(new UserModule(userA, userB));
    }

    public UserComponent getUserComponent(){
        return userComponent;
    }

    /**
     * 在合适位置，可调用来释放对象
     */
    public void releaseUserComponent(){
        userComponent = null;
    }


}
