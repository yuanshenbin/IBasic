package com.yuanshenbin.basic.log

import com.yuanshenbin.basic.config.BasicOptions


/**
 * author : yuanshenbin
 * time   : 2023/3/9
 * desc   :
 */
object ILog {
    private var instance: LogAbstract? = null

    @JvmStatic
    fun initialize(abstract: LogAbstract?): LogAbstract? {
        if (instance == null) {
            instance = abstract
        }
        return instance
    }

    @JvmStatic
    fun i(tag: String?, msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.i(tag, msg)
        }
    }

    @JvmStatic
    fun i(msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.i(msg)
        }
    }

    @JvmStatic
    fun e(tag: String?, msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.e(tag, msg)
        }
    }

    @JvmStatic
    fun e(msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.e(msg)
        }
    }

    @JvmStatic
    fun v(tag: String?, msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.v(tag, msg)
        }
    }

    @JvmStatic
    fun d(msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.d(msg)
        }
    }

    @JvmStatic
    fun d(tag: String?, msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.d(tag, msg)
        }
    }

    @JvmStatic
    fun w(msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.w(msg)
        }
    }

    @JvmStatic
    fun w(tag: String?, msg: String?) {
        if (BasicOptions.instance.isDebug()!!) {
            instance?.w(tag, msg)
        }
    }
}