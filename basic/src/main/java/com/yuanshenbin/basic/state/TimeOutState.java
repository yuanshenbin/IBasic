package com.yuanshenbin.basic.state;

import android.support.annotation.DrawableRes;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 超时状态
 */

public abstract class TimeOutState extends StateAbstract {

    public abstract void showTimeOut(@DrawableRes int resId, String... msg);

    public abstract void showTimeOut(@DrawableRes int resId);

    public abstract void showTimeOut(String... msg);
}
