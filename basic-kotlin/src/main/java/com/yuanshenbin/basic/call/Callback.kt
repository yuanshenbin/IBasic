package com.yuanshenbin.basic.call

/**
 * author : yuanshenbin
 * time   : 2018/4/5
 * desc   :
 */
abstract class Callback<T> {
    abstract fun ok(t: T)
    fun cancel(t: T) {}
    fun close() {}
}