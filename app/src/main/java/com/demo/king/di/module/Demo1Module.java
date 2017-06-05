package com.demo.king.di.module;

import com.demo.king.bean.BeanA;

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

}
