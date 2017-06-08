package com.demo.king.rxjava;

import android.util.Log;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 22:25
 */
public class Utils {

    public static void log(String msg){
        Log.e("daohen", msg);
    }

    public static void onCompleted(String... msg){
        Log.e("daohen", msg == null ? "onCompleted" : "onCompleted " + msg[0]);
    }

    public static void onError(String... msg){
        Log.e("daohen", msg == null ? "onError" : "onError " + msg[0]);
    }

    public static void onError(Throwable throwable){
        Log.e("daohen", throwable == null ? "onError" : "onError " + throwable.getMessage());
    }

    public static void onNext(String... msg){
        Log.e("daohen", msg == null ? "onNext" : "onNext " + msg[0]);
    }

    public static void onSuccess(String... msg){
        Log.e("daohen", msg == null ? "onSuccess" : "onSuccess " + msg[0]);
    }

    public static void currentThread(String tag){
        Log.e("daohen", tag + " thread="+Thread.currentThread().getName());
    }

}
