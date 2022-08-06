package com.yuanshenbin.basic.manager

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author : yuanshenbin
 * time   : 2018/4/19
 * desc   :
 */
object RxManager {
    fun <T> add(observable: Observable<T>, observer: Observer<T>?) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer!!)
    }

    fun <T> add(observable: Observable<T>) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}