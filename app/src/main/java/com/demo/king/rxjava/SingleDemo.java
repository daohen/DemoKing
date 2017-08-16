package com.demo.king.rxjava;


import com.demo.king.rxjava.internal.Net;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 10:17
 *
 * 暂没有考虑线程问题，都是在主线程中执行
 */
public class SingleDemo {

    public static void run(){
        m2Test();
    }

    private static void m2Test() {
        Single.just("sdfjdsf")
                .flatMap(new Function<String, SingleSource<String>>() {
                    @Override
                    public SingleSource<String> apply(@NonNull String s) throws Exception {
                        return Single.just(s + " end");
                    }
                }).subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Utils.log("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull String s) {
                Utils.log(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Utils.log("onError");
            }
        });
    }

    private static void m1Test(){
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<String> e) throws Exception {
                Callback callback = new Callback() {
                    @Override
                    public void onEvent(String msg) {
                        e.onSuccess(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        e.onError(new Throwable(msg));
                    }
                };

                e.setCancellable(Net.requestUrl(callback));
            }
        }).subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Utils.log("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull String s) {
                Utils.log(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Utils.log(e.getMessage());
            }
        });
    }

    public interface Callback{
        void onEvent(String msg);
        void onError(String msg);
    }
}
