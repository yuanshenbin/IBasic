package com.yuanshenbin.basic.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */
abstract class BasicViewHolder {
    open var mContext: Context

    constructor(rootView: ViewGroup) {
        mContext = rootView.context
    }

    constructor(rootView: View) {
        mContext = rootView.context
    }

    constructor(rootView: Window) {
        mContext = rootView.context
    }

    protected abstract fun initLayoutId(): Int

    fun IShowToast(str: String?) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show()
    }

    /**
     * 不带参数跳转
     */
    fun IStartActivity(sla: Class<*>?) {
        mContext.startActivity(Intent(mContext, sla))
    }

    /**
     * 带参数跳转
     */
    fun IStartActivity(bundle: Bundle?, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        mContext.startActivity(intent)
    }

    /**
     * 不带参数回传
     */
    fun IStartActivityForResult(requestCode: Int, sla: Class<*>?) {
        if (mContext is Activity) {
            (mContext as Activity).startActivityForResult(Intent(mContext, sla), requestCode)
        }
    }

    /**
     * 带参数回传
     */
    fun IStartActivityForResult(bundle: Bundle?, requestCode: Int, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        if (mContext is Activity) {
            (mContext as Activity).startActivityForResult(intent, requestCode)
        }
    }
}