package com.yuanshenbin.basic.config;

import android.graphics.Color;
import android.view.Gravity;

/**
 * author : yuanshenbin
 * time   : 2021-02-25
 * desc   : 提示框默认配置
 */
public abstract class TipsAbstract {

    public String getTitle() {
        return "提示";
    }

    public int getTitleColor() {
        return Color.parseColor("#000000");
    }

    public String getOkContentTitle() {
        return "确定";
    }

    public int getOkContentTitleColor() {
        return Color.parseColor("#333333");
    }

    public String getCancelContentTitle() {
        return "取消";
    }

    public int getCancelContentColor() {
        return Color.parseColor("#999999");
    }

    public int getTitleGravity() {
        return Gravity.LEFT;
    }

    public int getContentGravity() {
        return Gravity.LEFT;
    }

}
