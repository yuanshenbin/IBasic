package com.yuanshenbin.basic.call

/**
 * author : yuanshenbin
 * time   : 2018/4/5
 * desc   :
 */
abstract class Callback<T> {
    abstract fun ok(t: T)
    open fun cancel(t: T) {}
    open fun close() {}
}
