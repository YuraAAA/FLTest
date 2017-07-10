package com.dev24.flicker.utils;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {

    private static ExecutorService executorService;

    public static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                subscriber -> {
                    try {
                        subscriber.onNext(func.call());
                    } catch (Exception ex) {
                        Log.e("", "Error reading from the database", ex);
                        subscriber.onError(ex);
                    }
                    subscriber.onCompleted();
                });
    }

    private static ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(10);
        }
        return executorService;
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .retry(5)
                .subscribeOn(Schedulers.from(getExecutorService()))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<T, T> applyNotRetrySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<T, T> applySchedulersWithRetry() {

        return observable -> observable
                .retry(5)
                .subscribeOn(Schedulers.from(getExecutorService()))
                .observeOn(AndroidSchedulers.mainThread());
    }

    private RxUtils() {

    }

    public static <T> Observable.Transformer<T, T> applySchedulerSingle() {
        return observable -> observable
                .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
