package com.yuanshenbin.basic.util

import com.tencent.mmkv.MMKV

/**
 * author : yuanshenbin
 * time   : 2022-10-21
 * desc   : 代理sp，这样可以创建多个不一样的实力
 */
interface SPProxy {
    fun filter(key: String?): MMKV?
}