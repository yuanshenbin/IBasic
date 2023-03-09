package com.yuanshenbin.basic.config

import android.content.Context
import com.tencent.mmkv.MMKV
import com.yuanshenbin.basic.develop.DevelopConfig
import com.yuanshenbin.basic.develop.DevelopMode
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy
import com.yuanshenbin.basic.imgloader.ImageLoader
import com.yuanshenbin.basic.log.ILog
import com.yuanshenbin.basic.log.LogAbstract
import com.yuanshenbin.basic.log.LogImpl
import com.yuanshenbin.basic.util.SPProxy
import com.yuanshenbin.basic.util.SPUtils

/**
 * author : yuanshenbin
 * time   : 2021-01-25
 * desc   : 一些基础配置
 */
class BasicOptions {
    var pageSize = 20 //分页默认值
        private set
    var context: Context? = null
        private set
    var compressSize = 100
        //默认压缩大小
        private set
    var compressCount = 10
        //默认压缩次数
        private set
    private var mMMKV: MMKV? = null
    private val mIImageLoaderProxy: IImageLoaderProxy? = null
    var tipsAbstract: TipsAbstract = object : TipsAbstract() {}
        private set
    var spProxy: SPProxy? = null
        private set
    private var debug: Boolean? = false
    var developConfig: DevelopConfig? = null
    var logAbstract: LogAbstract = object : LogImpl() {}

    fun init(context: Context?, debug: Boolean): BasicOptions {
        this.context = context
        this.debug = debug;
        return this
    }

    fun isDebug(): Boolean? {
        return debug
    }

    fun logAbstract(abstract: LogAbstract?): BasicOptions {
        this.logAbstract = abstract!!
        return this
    }

    fun spProxy(spProxy: SPProxy?): BasicOptions {
        this.spProxy = spProxy
        return this
    }

    fun tipsDialogConfig(tipsAbstract: TipsAbstract): BasicOptions {
        this.tipsAbstract = tipsAbstract
        return this
    }

    fun developConfig(developConfig: DevelopConfig): BasicOptions {
        this.developConfig = developConfig
        return this
    }

    fun imageLoaderProxy(iImageLoaderProxy: IImageLoaderProxy?): BasicOptions {
        ImageLoader.instance
                .setImageLoaderProxy(iImageLoaderProxy)
        return this
    }

    fun pageSize(pageSize: Int): BasicOptions {
        this.pageSize = pageSize
        return this
    }

    fun compressSize(compressSize: Int): BasicOptions {
        this.compressSize = compressSize
        return this
    }

    fun compressCount(compressCount: Int): BasicOptions {
        this.compressCount = compressCount
        return this
    }

    fun build() {
        context!!
        ILog.initialize(logAbstract)
        if (mIImageLoaderProxy != null) {
            ImageLoader.instance
                    .setImageLoaderProxy(mIImageLoaderProxy)
        }
        if (developConfig != null) {
            DevelopMode
                    .getInstance()
                    .InitializationConfig(developConfig)
        }
        MMKV.initialize(context)
        if (spProxy != null) {
            SPUtils.initialize(spProxy)
        } else {
            mMMKV = MMKV.mmkvWithID(context!!.packageName)
            SPUtils.initialize(object : SPProxy {
                override fun filter(key: String?): MMKV? {
                    return mMMKV
                }
            })
        }
    }

    companion object {
        private var manager: BasicOptions? = null

        @JvmStatic
        val instance: BasicOptions
            get() {
                if (manager == null) {
                    synchronized(BasicOptions::class.java) {
                        manager = BasicOptions()
                    }
                }
                return manager!!
            }
    }
}