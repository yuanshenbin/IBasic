package com.yuanshenbin.basic.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author : yuanshenbin
 * time   : 2018/7/21
 * desc   :
 */

public class INetworkUtils {
    /**
     * 判断是否有网络
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            context = context.getApplicationContext();
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
