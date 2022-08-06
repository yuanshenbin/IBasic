package com.yuanshenbin.basic.call

import android.app.Dialog
import android.view.View

/**
 * author : yuanshenbin
 * time   : 2021-02-24
 * desc   :
 */
interface CustomDialogListener<Any> {
    fun onCustomLayout(rootView: View?, dialog: Dialog?)
    fun onOk(callback: Callback<Any>?)
    fun onCancel(callback: Callback<Any>?)
}