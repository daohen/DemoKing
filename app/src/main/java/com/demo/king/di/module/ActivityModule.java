package com.demo.king.di.module;

import com.demo.king.bean.BeanE;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 13:44
 */
@Module
public class ActivityModule {

    @Provides
    BeanE provideBeanE(){
        return new BeanE();
    }

}
