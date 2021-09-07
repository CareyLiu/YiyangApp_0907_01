package com.yiyang.cn.app;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by juan on 2018/07/27.
 */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    private static final String TAG = SimpleSubscriber.class.getSimpleName();

    public SimpleSubscriber() {
    }

    public SimpleSubscriber(Subscriber<?> subscriber) {
        super(subscriber);
    }

    public SimpleSubscriber(Subscriber<?> subscriber, boolean shareSubscriptions) {
        super(subscriber, shareSubscriptions);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
    }
}
