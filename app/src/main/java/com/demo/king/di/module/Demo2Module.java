package com.demo.king.di.module;

import com.demo.king.bean.BeanB;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 22:01
 */
@Module
public class Demo2Module {

    @Provides
    BeanB provideBeanB(){
        return new BeanB();
    }

}
