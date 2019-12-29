package com.yuanshenbin.basic.manager;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author : yuanshenbin
 * time   : 2017/12/21
 * desc   :
 */

public class RxBus {

    private Relay<Object> mBus = null;
    private static volatile RxBus mDefaultInstance;


    public RxBus() {
        mBus = PublishRelay.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }


    public <T> void send(T obj) {
        mBus.accept(obj);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext, Consumer<? super Throwable> onError) {
        return toObservable(eventType).observeOn(scheduler).subscribe(onNext, onError);
    }

    public <T> Disposable register(Class<T> eventType, Scheduler scheduler, Consumer<T> onNext) {
        return toObservable(eventType).observeOn(scheduler).subscribe(onNext);
    }

    public <T> Disposable register(Class<T> eventType, Consumer<T> onNext) {
        return toObservable(eventType).subscribe(onNext);
    }

    public <T> Disposable register(BusConsumer<T> onNext, Consumer<? super Throwable> onError) {
        return toObservable(onNext).subscribe(onNext, onError);
    }

    public <T> Disposable register(Scheduler scheduler, BusConsumer<T> onNext, Consumer<? super Throwable> onError) {
        return toObservable(onNext).observeOn(scheduler).subscribe(onNext, onError);
    }

    public <T> Disposable register(Scheduler scheduler, BusConsumer<T> onNext) {
        toObservable(onNext).observeOn(scheduler).subscribe(onNext);
        return toObservable(onNext).observeOn(scheduler).subscribe(onNext);
    }

    public <T> Disposable register(BusConsumer<T> onNext) {
        return toObservable(onNext).subscribe(onNext);
    }


    public <T> Disposable registerAsync(BusOnNextConsumer<T> doNext, BusConsumer<T> onNext) {
        return toObservable(onNext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(doNext)
                .subscribe(onNext);
    }

    private <T> Observable<T> toObservable(BusConsumer<T> l) {
        //获取子类字节码文件对象，this代表的是子类对象。
        Class clazz = l.getClass();

        //获取子类所属接口的参数化类型,cn.xxx.xxx.BasicAction<cn.xxx.xxx.Standard>
        Type type = clazz.getGenericSuperclass();

        //因为type是顶级接口没有定义任何方法，所以需要强转为子接口ParameterizedType
        ParameterizedType parameterizedType = (ParameterizedType) type;

        //通过子接口定义的getActualTypeArguments方法获取到实际参数类型,<cn.xxx.xxx.Standard>
        //返回参数为数组，因为Java中接口可以多实现
        Type[] types = parameterizedType.getActualTypeArguments();

        //获取数组中的实际参数类型
        Class clzz = (Class) types[0];
        return mBus.ofType(clzz);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

}
