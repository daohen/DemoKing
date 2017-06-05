package com.demo.king.di.component;

import com.demo.king.SecondActivity;
import com.demo.king.di.module.Demo3Module;

import dagger.Subcomponent;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 23:46
 *
 * 此处要注意下用的是@Subcomponent，是一个子Component，依赖于一个父Component
 * 子Component可以访问所有父Component可以provide的对象，
 * 而父Component却不可访问子Component的provide对象
 */
@Subcomponent(modules = Demo3Module.class)
public interface Demo3Component {
    void inject(SecondActivity activity);
}
