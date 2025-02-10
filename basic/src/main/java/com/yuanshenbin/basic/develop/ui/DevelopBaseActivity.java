package com.yuanshenbin.basic.develop.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yuanshenbin.basic.develop.DevelopMode;

import io.reactivex.annotations.Nullable;


/**
 * author : yuanshenbin
 * time   : 2018/8/30
 * desc   :
 */

abstract class DevelopBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DevelopMode.getInstance().add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DevelopMode.getInstance().remove(this);
    }
}
