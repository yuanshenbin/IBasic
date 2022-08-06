package com.yuanshenbin.basic.imgloader

import android.view.View

/**
 * author : yuanshenbin
 * time   : 2021-09-24
 * desc   :
 */
interface IImageLoaderProxy {
    fun displayImage(url: Any?, view: View?)
    fun placeholder(resId: Int)
    fun options(l: ImageOptions?)
    val cacheSize: Long
    fun clearCache()
}