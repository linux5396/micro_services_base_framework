package com.linxu.provider;


import rx.Observable;
import rx.Subscriber;

/**
 * @author linxu
 * @date 2019/7/12
 */
public class ObServableTest {


    public static void main(String[] args) {
        Observable<String> source=Observable.create(subscriber -> {
            subscriber.onNext("Hello !");
            subscriber.onNext("welcome to subscribe it.");
            subscriber.onError(new NullPointerException("error"));
            subscriber.onCompleted();
        });
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println(throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.err.println(s);
            }
        };
        source.subscribe(subscriber);
    }
}
