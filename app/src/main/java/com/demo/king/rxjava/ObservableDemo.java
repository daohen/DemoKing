package com.demo.king.rxjava;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.FuncN;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/07 22:26
 */
public class ObservableDemo {

    public static void run(){
        m5Test();
    }

    private static void m15Test(){
        List<String> list;
    }

    private static void m14Test() {
        Observable<String> o = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("344");
            }
        });

        //每次订阅都是一个新的observable
        o.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Utils.log(s);
            }
        });

        o.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }


    public void m13Test(){
        Observable.just(1,2,3,4)
                .compose(this.<Integer>applySchedulers())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Utils.onNext(integer.toString());
                    }
                });
    }

    public static <T> Observable.Transformer<T, T> applySchedulers(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static void m12Test(){
        Observable.create(SyncOnSubscribe.createSingleState(new Func0<String>() {
            @Override
            public String call() {
                Utils.log("call thread="+Thread.currentThread().getName());
                return "123";
            }
        }, new Action2<String, Observer<? super Integer>>() {
            @Override
            public void call(String s, Observer<? super Integer> observer) {
                Utils.log("call2 thread="+Thread.currentThread().getName());
                observer.onNext(Integer.valueOf(s));
                observer.onCompleted();
            }
        })).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Utils.log("call3 thread="+Thread.currentThread().getName());
                Utils.log("call "+integer);
            }
        });

        /*Observable.create(SyncOnSubscribe.createStateless(new Action1<Observer<? super String>>() {
            @Override
            public void call(Observer<? super String> observer) {
                //可用来做网络请求
                observer.onNext("aa");
                observer.onCompleted();
            }
        })).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Utils.onNext(s);
            }
        });*/

        /*Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "aab";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Utils.onNext(s);
            }
        });*/

    }

    public static void m11Test(){
        File file = new File("/etc");
        Observable.just(file)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return file.isFile();
            }
        }).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(File file) {
                Utils.log(file.getPath());
            }
        });
    }

    public static void m10Test(){
        Observable.just(2,4,6,8,20)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 6;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Utils.log("call "+aBoolean);
            }
        });
    }

    public static void m9Test(){
        Observable.sequenceEqual(
                Observable.just(1, 2, 3, 4),
                Observable.just(10, 20, 30, 40),
                new Func2<Integer, Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer, Integer integer2) {
                        return integer * 10 == integer2;
                    }
                }
        ).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                Utils.log("call "+aBoolean);
            }
        });
    }

    public static void m8Test(){
        Observable.range(40, 5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Utils.log("call "+integer);
                    }
                });
    }

    public static void m7Test(){
        Observable.just(23).nest()
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                Utils.log("call="+integer);
                            }
                        });
                    }
                });
    }

    public static void m6Test(){
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Utils.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Utils.onError(e);
            }

            @Override
            public void onNext(Long aLong) {
                Utils.onNext("value="+aLong);

                if (aLong > 5){
                    unsubscribe();
                }
            }
        });
    }

    /**
     * * 传入多个observable，还有一个合并的函数
     * （发射的前提是其它observable都发送过数据，就像最后一个observable是第一次发送数据，
     * 在接收到这最后一个发送过来的数据时，会第一次触发发送数据到订阅者那里，以后，
     * 每接收到一次observable发送过来的数据都会向订阅者发送数据，而这些数据在合并函数里面的值，
     * 以每个observable最近传过来的值为准）
     */
    public static void m5Test(){
        Observable<Integer> o1 = Observable.from(new Integer[]{1,2}).delay(2, TimeUnit.SECONDS);

        Observable<Integer> o2 = Observable.from(new Integer[]{3,4}).delay(5, TimeUnit.SECONDS);

        Observable<Integer> o3 = Observable.from(new Integer[]{5,6});

        Observable.combineLatest(o1, o2, o3, new Func3<Integer, Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2, Integer integer3) {
                return integer + "*" + integer2 + "*" + integer3;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String string) {
                Utils.onNext(string);
            }
        });
    }

    /**
     * 谁先发送到就接收谁，其它舍弃
     */
    public static void m4Test(){
        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("aa");
            }
        }).delay(500, TimeUnit.MILLISECONDS);

        Observable<String> observable2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("bb");
            }
        });

        Observable.amb(observable1, observable2)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Utils.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        Utils.onNext(s);
                    }
                });

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
