package com.yuanshenbin.basic.state

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.annotation.LayoutRes

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   :
 */
abstract class StateAbstract {
    protected var mContext: Context? = null
    protected var root: LinearLayout? = null

    @LayoutRes
    abstract fun onCreateView(): Int
    open  fun onViewCreated(context: Context?, root: LinearLayout) {
        mContext = context
        this.root = root
        if (root.visibility == View.GONE) {
            root.visibility = View.VISIBLE
        }

        removeViews(root)
        val viewStub = ViewStub(context)
        viewStub.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewStub.layoutResource = onCreateView()
        root.addView(viewStub)
        viewStub.inflate()
    }

    fun removeViews(root: LinearLayout) {
        root.removeAllViews()
    }
}