package com.yuanshenbin.basic.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * author : yuanshenbin
 * time   : 2018/7/21
 * desc   :
 */
class NetworkUtils  {
    companion object {
        /**
         * 判断是否有网络
         */
        @JvmStatic
        fun isNetworkConnected(context: Context?): Boolean {
            var context = context
            if (context != null) {
                context = context.applicationContext
                val manager = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = manager.activeNetworkInfo
                if (info != null) {
                    return info.isAvailable
                }
            }
            return false
        }
    }

}