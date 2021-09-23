package com.yuanshenbin.basic.call;

import android.app.Dialog;
import android.view.View;

/**
 * author : yuanshenbin
 * time   : 2021-02-24
 * desc   :
 */
public interface CustomDialogListener {
    void onCustomLayout(View rootView, Dialog dialog);

    void onOk(Callback callback);

    void onCancel(Callback callback);
}

