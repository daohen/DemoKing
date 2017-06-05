package com.demo.king.di.module;

import com.demo.king.bean.BeanC;

import dagger.Module;
import dagger.Provides;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 00:02
 */
@Module
public class Demo3Module {

    @Provides
    BeanC provideBeanC(){
        return new BeanC();
    }
}
