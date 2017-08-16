package com.demo.king.rxjava.internal;

import com.demo.king.rxjava.SingleDemo;

import io.reactivex.functions.Cancellable;


/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 23:24
 */
public class Net {

    /**
     * 模拟网络请求
     * @param callback
     * @return
     */
    public static Cancellable requestUrl(SingleDemo.Callback callback){
        callback.onEvent("succ");
        return new Cancellable() {
            @Override
            public void cancel() throws Exception {

            }
        };
    }

}
