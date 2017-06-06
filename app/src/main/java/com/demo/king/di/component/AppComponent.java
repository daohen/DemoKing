package com.demo.king.di.component;

import android.content.Context;

import com.demo.king.bean.Repository;
import com.demo.king.di.module.AppModule;
import com.demo.king.di.module.UserModule;
import com.google.gson.Gson;

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
     * 如果在声明其它Component时，用@Component(dependencies = AppComponent.class, module...)声明
     * 表示它依赖于AppComponent，AppComponent暴露出的方法，子Component都可以访问
     */

    /**
     * 这里的方法声明一般有下面几种方式
     * 1. 很长见的像 void inject(MainActivity a)
     * 2. 返回一个对象，像下面这个方法
     */
    Demo3Component plusDemo3Component();

    UserComponent plusUserComponent(UserModule userModule);

    /**
     * 返回系统类的实例，无法用@Inject标记的构造器生成，只能通过module查找，
     * AppModule里面有provide Context的方法
     * @return
     */
    Context context();

    /**
     * 自定义的类，构造函数如果没有参数，可以直接用@Inject标记构造器生成，而不用去写在module里面通过provide生成
     * 如果构造函数有参数，根据它的原则先从module里面找，再从@Inject构造器标记去找
     * @return
     */
    Repository repository();

    Gson gson();

}
