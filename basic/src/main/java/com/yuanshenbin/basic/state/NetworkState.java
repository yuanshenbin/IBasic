package com.yuanshenbin.basic.state;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 网络异常状态
 */

public abstract class NetworkState extends StateAbstract {
    protected OnRetryListener listener;
    
    public NetworkState(@Nullable OnRetryListener listener) {
        this.listener = listener;
    }

    public abstract void showNetwork(@DrawableRes int resId, String... msg);

    public abstract void showNetwork(@DrawableRes int resId);

    public abstract void showNetwork(String... msg); 
    
}
