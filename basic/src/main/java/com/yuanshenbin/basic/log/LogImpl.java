package com.yuanshenbin.basic.log;

import android.util.Log;

public class LogImpl  extends LogAbstract{
    @Override
    void i(String tag, String msg) {
        Log.i(tag,msg);
    }

    @Override
    void i(String msg) {
        Log.i(TAG,msg);
    }

    @Override
    void e(String tag, String msg) {
        Log.e(tag,msg);
    }

    @Override
    void e(String msg) {
        Log.e(TAG,msg);
    }

    @Override
    void d(String tag, String msg) {
        Log.d(tag,msg);
    }

    @Override
    void d(String msg) {
        Log.d(TAG,msg);
    }

    @Override
    void v(String tag, String msg) {
        Log.v(tag,msg);
    }

    @Override
    void v(String msg) {
        Log.v(TAG,msg);
    }

    @Override
    void w(String tag, String msg) {
        Log.w(tag,msg);
    }

    @Override
    void w(String msg) {
        Log.e(TAG,msg);
    }
}
