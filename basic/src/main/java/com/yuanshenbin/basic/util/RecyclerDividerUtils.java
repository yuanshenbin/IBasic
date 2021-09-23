package com.yuanshenbin.basic.util;

import android.content.Context;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.widget.IDividerItemDecoration;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


/**
 * author : yuanshenbin
 * time   : 2018/8/10
 * desc   :
 */

public class RecyclerDividerUtils {

    public static IDividerItemDecoration getDivider1px(Context context) {
        IDividerItemDecoration decoration = new IDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.basic_item_divider_1px));
        return decoration;
    }

    public static IDividerItemDecoration getDivider(Context context, @DrawableRes int id) {
        IDividerItemDecoration decoration = new IDividerItemDecoration(context, RecyclerView.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, id));
        return decoration;
    }
}
