package com.demo.king.di.component;

import com.demo.king.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:32
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 这里的方法声明一般有下面几种方式
     * 1. 很长见的像 void inject(MainActivity a)
     * 2. 返回一个对象，像下面这个方法
     */
    Demo3Component plusDemo3Component();

}
