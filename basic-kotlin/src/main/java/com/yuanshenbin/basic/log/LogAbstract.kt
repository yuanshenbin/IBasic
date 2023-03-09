package com.yuanshenbin.basic.log

/**
 * author : yuanshenbin
 * time   : 2023/3/9
 * desc   :
 */
abstract class LogAbstract {
    abstract fun i(tag: String?, msg: String?)
    abstract fun i(msg: String?)
    abstract fun e(tag: String?, msg: String?)
    abstract fun e(msg: String?)
    abstract fun d(tag: String?, msg: String?)
    abstract fun d(msg: String?)
    abstract fun v(tag: String?, msg: String?)
    abstract fun v(msg: String?)
    abstract fun w(tag: String?, msg: String?)
    abstract fun w(msg: String?)
    val tag: String
        get() = "ysb"
}