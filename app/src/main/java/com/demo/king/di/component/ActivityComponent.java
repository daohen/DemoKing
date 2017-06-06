package com.demo.king.di.component;

import com.demo.king.ThirdActivity;
import com.demo.king.di.PerActivity;
import com.demo.king.di.module.ActivityModule;

import dagger.Component;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 13:44
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ThirdActivity activity);

}
