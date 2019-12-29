package com.yuanshenbin.basic.delegate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.yuanshenbin.basic.state.OnRetryListener;
import com.yuanshenbin.basic.state.StateLayoutManager;

import java.io.Serializable;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

public abstract class BaseActivityDelegate extends YJPDelegate implements Serializable {

    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    public void onStatusBar(AppCompatActivity activity) {
    }

    public StateLayoutManager getStateLayoutManager(AppCompatActivity root, OnRetryListener listener) {
        return null;
    }

    public void onConfig(AppCompatActivity activity) {

    }

    public void onRestart() {
    }

    public void onDestroy() {
    }

    public void finish(){}


}
