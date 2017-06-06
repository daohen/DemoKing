package com.demo.king.di.module;

import com.demo.king.bean.BeanA;
import com.demo.king.di.BeanA1;
import com.demo.king.di.BeanA2;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 21:12
 */
@Module
public class Demo1Module {


    @Provides
    BeanA provideBeanA(){
        return new BeanA();
    }

    /**
     * 下面是提供同一个类的不同实例的另外的表现形式
     * 和@Named一样，只是写法不同，这里不用再去标记名字
     * @return
     */

    @Provides @BeanA1 BeanA provideBeanA1(){
        return new BeanA();
    }

    @Provides @BeanA2 BeanA provideBeanA2(){
        return new BeanA();
    }
}
