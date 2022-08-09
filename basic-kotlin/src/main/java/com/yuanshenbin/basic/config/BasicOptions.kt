package com.yuanshenbin.basic.config

import android.content.Context
import com.tencent.mmkv.MMKV
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy
import com.yuanshenbin.basic.imgloader.ImageLoader

/**
 * author : yuanshenbin
 * time   : 2021-01-25
 * desc   : 一些基础配置
 */
class BasicOptions {
    var pageSize  = 20 //分页默认值
        private set
    var context: Context? = null
        private set
    private val mIImageLoaderProxy: IImageLoaderProxy? = null
    var tipsAbstract: TipsAbstract = object : TipsAbstract() {}
        private set
    var mMKVAbstract: MMKVAbstract? = null
        private set

    fun init(context: Context?): BasicOptions {
        this.context = context
        return this
    }

    fun MMKVConfig(MMKVAbstract: MMKVAbstract?): BasicOptions {
        mMKVAbstract = MMKVAbstract
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
        if (mMKVAbstract != null) {
            MMKV.initialize(context)
        } else {
            MMKV.initialize(context)
        }
    }

    fun pageSize(pageSize: Int) {
        this.pageSize = pageSize
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