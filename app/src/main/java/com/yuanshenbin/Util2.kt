package com.yuanshenbin

import com.yuanshenbin.ibasic.BuildConfig

/**
 * author : yuanshenbin
 * time   : 2022-01-26
 * desc   :
 */

object Util2 {

    @JvmStatic
    fun getName1(): String {
        return BuildConfig.VERSION_NAME
    }
}