package com.yuanshenbin.basic.state;


import android.support.annotation.DrawableRes;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 空数据状态
 */

public abstract class EmptyState extends StateAbstract {
    protected OnRetryListener listener;

    public abstract void showEmpty(@DrawableRes int resId, String... msg);

    public abstract void showEmpty(@DrawableRes int resId);

    public abstract void showEmpty(CharSequence... msg);
}
