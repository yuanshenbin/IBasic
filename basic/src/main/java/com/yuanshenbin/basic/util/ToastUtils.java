package com.yuanshenbin.basic.util;

import android.content.Context;
import android.widget.Toast;

/**
 * author : yuanshenbin
 * time   : 2018/8/27
 * desc   : toast
 */
public class ToastUtils {

    private static Toast toast = null;

    public static void shortToast(Context context, String text) {
        if (Utils.isEmpty(text)) {
            return;
        }
        context = context.getApplicationContext();
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }


}
