package com.yuanshenbin.basic.manager;


import io.reactivex.Observer;

/**
 * author : yuanshenbin
 * time   : 2018/10/25
 * desc   :
 */

public abstract class BusObserver<T> implements Observer<T> {


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
