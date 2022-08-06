package com.yuanshenbin.basic.AvoidOnResult;

import android.content.Intent;

/**
 * Created by jack on 2017/12/27.
 * {@link https://github.com/AnotherJack/AvoidOnResult}
 */

public class ActivityResultInfo {
    private int resultCode;
    private int requestCode;
    private Intent data;

    public ActivityResultInfo(int resultCode, Intent data) {
        this.resultCode = resultCode;
        this.data = data;
    }
    public ActivityResultInfo(int resultCode, int requestCode, Intent data) {
        this.resultCode = resultCode;
        this.requestCode = requestCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {

        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
