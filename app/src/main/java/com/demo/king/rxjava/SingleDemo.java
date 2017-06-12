package com.demo.king.rxjava;


import android.util.Log;

import com.demo.king.rxjava.internal.Net;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleEmitter;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 10:17
 *
 * 暂没有考虑线程问题，都是在主线程中执行
 */
public class SingleDemo {

    public static void run(){
        m12Test();
    }


    public static void m12Test(){
        Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                Utils.log("call");
                singleSubscriber.onSuccess("succ");
            }
        }).delay(5, TimeUnit.SECONDS)
                .subscribe(new SingleSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Utils.onSuccess(s);
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
    }

    public static void m11Test(){
        Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
//                singleSubscriber.onSuccess("succ");
                singleSubscriber.onError(new Throwable("null"));
            }
        }).doOnEach(new Action1<Notification<? extends String>>() {
            @Override
            public void call(Notification<? extends String> notification) {
                Utils.log("doOnEach");
            }
        }).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                Utils.onSuccess(s);
            }

            @Override
            public void onError(Throwable error) {
                Utils.onError(error);
            }
        });
    }

    public static void m10Test(){
        Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onError(new Throwable("is null"));
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Utils.log("doOnError");
            }
        }).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                Utils.onSuccess(s);
            }

            @Override
            public void onError(Throwable error) {
                Utils.onError(error);
            }
        });
    }

    public static void m9Test(){
        Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("succ");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Utils.onCompleted();
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

    public static void m8Test(){
        Single<String> single = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
//                singleSubscriber.onSuccess("bbbb");
                singleSubscriber.onError(new Throwable("bbb"));
            }
        });

        single.onErrorReturn(new Func1<Throwable, String>() {
            @Override
            public String call(Throwable throwable) {
                return "error";
            }
        }).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                Utils.onSuccess(s);
            }

            @Override
            public void onError(Throwable error) {
                Utils.onError(error.getMessage());
            }
        });
    }

    public static void m7Test(){
        Single<String> single1 = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("alun");
            }
        });

        Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("bill");
            }
        }).concatWith(single1)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Utils.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.onNext(s);
                    }
                });
    }

    public static void m6Test(){
        Single<String> single1 = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("44");
            }
        });
        Single<String> single2 = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("22");
            }
        });
        Single.zip(single1, single2, new Func2<String, String, Integer>() {
            @Override
            public Integer call(String s, String s2) {
                return Integer.valueOf(s) + Integer.valueOf(s2);
            }
        }).subscribe(new SingleSubscriber<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                Utils.onSuccess(Integer.toString(integer));
            }

            @Override
            public void onError(Throwable error) {

            }
        });

    }

    public interface Callback{
        void onEvent(String msg);
        void onError(String msg);
    }
    public static void m5Test(){
        Single<String> single = Single.fromEmitter(new Action1<SingleEmitter<String>>() {
            @Override
            public void call(final SingleEmitter<String> stringSingleEmitter) {
                Callback callback = new Callback() {
                    @Override
                    public void onEvent(String msg) {
                        stringSingleEmitter.onSuccess(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        stringSingleEmitter.onError(new Throwable(msg));
                    }
                };

                Cancellable cancellable = Net.requestUrl(callback);
                stringSingleEmitter.setCancellation(cancellable);
            }
        });

        single.subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                Utils.onSuccess(s);
            }

            @Override
            public void onError(Throwable error) {
                Utils.onError(error.getMessage());
            }
        });
    }

    /**
     * Single.fromCallable()
     */
    public static void m4Test(){
        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "fromCallable";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Utils.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Utils.onError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Utils.onNext(s);
            }
        });
    }

    /**
     * Single.concat()
     */
    public static void m3Test(){
        Single<String> single1 = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                singleSubscriber.onSuccess("single1");
            }
        });

        Single<String> single2 = Single.create(new Single.OnSubscribe<Integer>() {
            @Override
            public void call(SingleSubscriber<? super Integer> singleSubscriber) {
                singleSubscriber.onSuccess(399);
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return Integer.toString(integer);
            }
        });

        Single.concat(single1, single2)
                .subscribe(new Subscriber<String>() {
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
                });
    }

    /**
     * Single.error()
     */
    public static void m2Test(){
        Single.error(new NullPointerException("null null"))
                .subscribe(new SingleSubscriber<Object>() {
                    @Override
                    public void onSuccess(Object o) {

                    }

                    @Override
                    public void onError(Throwable error) {
                        Utils.onError(error.getMessage());
                    }
                });

    }

    /**
     * single.lift()
     */
    public static void m1Test(){
        Single<String> single = Single.create(new Single.OnSubscribe<String>() {
            @Override
            public void call(SingleSubscriber<? super String> singleSubscriber) {
//                String[] strings = new String[]{"alun", "bill", "lucy", "alpha", "bue"};
//                singleSubscriber.onSuccess(Arrays.asList(strings));
                singleSubscriber.onSuccess("single");
            }
        });




        single = single.lift(new Observable.Operator<String, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(String string) {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onNext("lift "+string);
                    }
                };
            }
        });

        SingleSubscriber<String> singleSubscriber = new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String s) {
                Utils.onSuccess(s);
            }

            @Override
            public void onError(Throwable error) {

            }
        };

        single.subscribe(singleSubscriber);
    }
}
