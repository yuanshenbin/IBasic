package com.yuanshenbin.basic.log

import android.util.Log

/**
 * author : yuanshenbin
 * time   : 2023/3/9
 * desc   :
 */
open class LogImpl : LogAbstract() {
    override fun i(tag: String?, msg: String?) {
        Log.i(tag, msg!!)
    }

    override fun i(msg: String?) {
        Log.i(tag, msg!!)
    }

    override fun e(tag: String?, msg: String?) {
        Log.e(tag, msg!!)
    }

    override fun e(msg: String?) {
        Log.e(tag, msg!!)
    }

    override fun d(tag: String?, msg: String?) {
        Log.d(tag, msg!!)
    }

    override fun d(msg: String?) {
        Log.d(tag, msg!!)
    }

    override fun v(tag: String?, msg: String?) {
        Log.v(tag, msg!!)
    }

    override fun v(msg: String?) {
        Log.v(tag, msg!!)
    }

    override fun w(tag: String?, msg: String?) {
        Log.w(tag, msg!!)
    }

    override fun w(msg: String?) {
        Log.w(tag, msg!!)
    }
}