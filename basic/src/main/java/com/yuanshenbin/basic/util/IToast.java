package com.yuanshenbin.basic.util;

import android.content.Context;
import android.widget.Toast;


public class IToast {

    private static Toast toast = null;

    public static void showToast(Context context, String text) {
        if (IUtils.isEmpty(text)) {
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
