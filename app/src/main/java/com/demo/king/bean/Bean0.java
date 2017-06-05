package com.demo.king.bean;

import android.util.Log;

import javax.inject.Inject;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/05 22:44
 */
public class Bean0 {

    /**
     * 只能有一个构造函数标记@Inject
     */
    @Inject
    public Bean0(){
        Log.e("daohen", "Bean0");
    }

    public Bean0(String a){

    }

}
