package com.yuanshenbin.basic.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.yijiupi.core.basic.R;
import com.yijiupi.core.basic.widget.YJPDividerItemDecoration;


/**
 * author : yuanshenbin
 * time   : 2018/8/10
 * desc   :
 */

public class RecyclerDividerUtils {

    public static YJPDividerItemDecoration getDivider1px(Context context) {
        YJPDividerItemDecoration decoration = new YJPDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.core_basic_item_divider_1px));
        return decoration;
    }

    public static YJPDividerItemDecoration getDivider(Context context, @DrawableRes int id) {
        YJPDividerItemDecoration decoration = new YJPDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, id));
        return decoration;
    }
}
