package com.yuanshenbin.basic.config

import android.content.Context
import com.tencent.mmkv.MMKV
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy
import com.yuanshenbin.basic.imgloader.ImageLoader
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

    fun init(context: Context?): BasicOptions {
        this.context = context
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

    fun imageLoaderProxy(iImageLoaderProxy: IImageLoaderProxy?): BasicOptions {
        ImageLoader.instance
                .setImageLoaderProxy(iImageLoaderProxy)
        return this
    }

    fun build() {
        if (mIImageLoaderProxy != null) {
            ImageLoader.instance
                    .setImageLoaderProxy(mIImageLoaderProxy)
        }
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

    fun pageSize(pageSize: Int) {
        this.pageSize = pageSize
    }

    fun compressSize(compressSize: Int) {
        this.compressSize = compressSize
    }

    fun compressCount(compressCount: Int) {
        this.compressCount = compressCount
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