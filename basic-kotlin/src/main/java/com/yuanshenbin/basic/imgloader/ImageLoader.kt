package com.yuanshenbin.basic.imgloader

import android.widget.ImageView

/**
 * author : yuanshenbin
 * time   : 2021-09-24
 * desc   : 抽象的图片加载期
 */

class ImageLoader {
    private var mIImageLoaderProxy: IImageLoaderProxy? = null
    fun setImageLoaderProxy(IImageLoaderProxy: IImageLoaderProxy?) {
        mIImageLoaderProxy = IImageLoaderProxy
    }

    fun displayImage(url: Any?, view: ImageView?) {
        mIImageLoaderProxy!!.displayImage(url, view)
    }

    fun placeholder(resId: Int): ImageLoader {
        mIImageLoaderProxy!!.placeholder(resId)
        return this
    }

    fun options(l: ImageOptions?): ImageLoader {
        mIImageLoaderProxy!!.options(l)
        return this
    }

    val cacheSize: Long
        get() = mIImageLoaderProxy!!.cacheSize

    fun clearCache() {
        mIImageLoaderProxy!!.clearCache()
    }

    companion object {
        private var manager: ImageLoader? = null
        @JvmStatic
        val instance: ImageLoader
            get() {
                if (manager == null) {
                    synchronized(ImageLoader::class.java) {
                        manager = ImageLoader()
                    }
                }
                return manager!!
            }
    }
}