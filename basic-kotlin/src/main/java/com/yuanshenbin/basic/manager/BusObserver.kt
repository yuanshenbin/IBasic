package com.yuanshenbin.basic.manager

import io.reactivex.Observer

/**
 * author : yuanshenbin
 * time   : 2018/10/25
 * desc   :
 */
abstract class BusObserver<T> : Observer<T> {
    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}