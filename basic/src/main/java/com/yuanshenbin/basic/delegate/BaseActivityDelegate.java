package com.yuanshenbin.basic.delegate;


import android.os.Bundle;

import com.yuanshenbin.basic.state.OnEmptyListener;
import com.yuanshenbin.basic.state.OnRetryListener;
import com.yuanshenbin.basic.state.StateLayoutManager;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

public abstract class BaseActivityDelegate extends IDelegate implements Serializable {

    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    public void onStatusBar(AppCompatActivity activity) {
    }

    public StateLayoutManager getStateLayoutManager(AppCompatActivity root, OnRetryListener retryListener,  OnEmptyListener emptyListener) {
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
