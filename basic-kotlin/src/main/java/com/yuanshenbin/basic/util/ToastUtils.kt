package com.yuanshenbin.basic.util

import android.content.Context
import android.widget.Toast

/**
 * Toast帮助类
 *
 * @author back
 * @version landingtech_v1
 */

class ToastUtils  {

    companion object {
        private var toast: Toast? = null
        @JvmStatic
        fun shortToast(context: Context, text: String?) {
            var context = context
            if (Utils.isEmpty(text)) {
                return
            }
            context = context.applicationContext
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            } else {
                toast?.setText(text)
            }
            toast?.show()

        }
    }
}
