package com.yuanshenbin.basic.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yuanshenbin.basic.R
import com.yuanshenbin.basic.widget.IDividerItemDecoration

/**
 * author : yuanshenbin
 * time   : 2018/8/10
 * desc   :
 */
class RecyclerDividerUtils  {

    companion object {
        @JvmStatic
        fun getDivider1px(context: Context): IDividerItemDecoration {
            val decoration = IDividerItemDecoration(context, RecyclerView.VERTICAL)
            decoration.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.basic_item_divider_1px)!!)
            return decoration
        }
        @JvmStatic
        fun getDivider(context: Context, @DrawableRes id: Int): IDividerItemDecoration {
            val decoration = IDividerItemDecoration(context, RecyclerView.VERTICAL)
            decoration.setDrawable(ContextCompat.getDrawable(context!!, id)!!)
            return decoration
        }


    }
}