package com.demo.king.bean;

import android.util.Log;

import javax.inject.Inject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 10:14
 */
public class Bean1 {

    @Inject
    public Bean1(){
        Log.e("daohen", Bean1.class.getSimpleName());
    }

}
