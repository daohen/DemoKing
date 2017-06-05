package com.demo.king.di.module;

import com.demo.king.bean.BeanD;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:32
 */
@Module
public class AppModule {

    @Provides
    BeanD provideBeanD(){
        return new BeanD();
    }
}
