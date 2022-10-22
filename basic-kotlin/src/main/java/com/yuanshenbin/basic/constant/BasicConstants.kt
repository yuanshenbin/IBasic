package com.yuanshenbin.basic.constant

import com.yuanshenbin.basic.config.BasicOptions

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

class BasicConstants {


    companion object {

        const val LOADING_STATE1 = 1 //没有数据
        const val LOADING_STATE2 = 2 //分页 不需要全局loading//或者有数据下啦也不需要loading
        const val LOADING_STATE3 = 3 //有数据加载无背景的loading
        const val LOADING_STATE4 = 4 //有数据下啦，没有loading

        @JvmField
        val PAGESIZE: Int = BasicOptions.instance.pageSize
        @JvmField
        val COMPRESS_COUNT : Int = BasicOptions.instance.compressCount
        @JvmField
        val COMPRESS_SIZE : Int = BasicOptions.instance.compressSize
        const val DEFAULT = "default"
        const val DEFAULT_ERROR = "default_error" //rxjava会用到区分请求和自己抛出异常
    }


}