package com.demo.king.rxjava;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 22:26
 */
public class ObservableDemo {

    public static void run(){
        m3Test();
    }

    public static void m3Test(){
        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                List<String> stringList = Arrays.asList("aa","bb","cc","dd","ee");
                for (String string : stringList){
                    subscriber.onNext(string);
                }
                subscriber.onCompleted();
            }
        });

        Observable<String> observable2 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7);
                for (Integer integer : integers){
                    subscriber.onNext(integer);
                }
                subscriber.onCompleted();
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "value = "+Integer.toString(integer);
            }
        });

        Observable.concat(observable1, observable2)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Utils.onNext(s);
                    }
                });
    }

    public static void m2Test(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("aaa");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("daohen", s);
            }
        };

        observable.subscribe(subscriber);
    }

    public static void m1Test(){
        Observable.just(null).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Utils.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Utils.onError(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                Utils.onNext();
            }
        });
    }
}
