package com.yuanshenbin.basic.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.IDividerItemDecoration;


/**
 * author : yuanshenbin
 * time   : 2018/8/10
 * desc   :
 */

public class RecyclerDividerUtils {

    public static IDividerItemDecoration getDivider1px(Context context) {
        IDividerItemDecoration decoration = new IDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.core_basic_item_divider_1px));
        return decoration;
    }

    public static IDividerItemDecoration getDivider(Context context, @DrawableRes int id) {
        IDividerItemDecoration decoration = new IDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, id));
        return decoration;
    }
}
