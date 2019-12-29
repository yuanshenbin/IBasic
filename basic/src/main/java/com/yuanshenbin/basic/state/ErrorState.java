package com.yuanshenbin.basic.state;

import android.support.annotation.DrawableRes;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 错误状态
 */

public abstract class ErrorState extends StateAbstract {

    public abstract void showError(@DrawableRes int resId, String... msg);

    public abstract void showError(@DrawableRes int resId);

    public abstract void showError(String... msg);
}
