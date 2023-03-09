package com.yuanshenbin.basic;


import android.os.Bundle;

import com.yuanshenbin.TestVH;
import com.yuanshenbin.basic.base.BaseActivity;
import com.yuanshenbin.basic.delegate.BaseActivityDelegate;
import com.yuanshenbin.basic.log.ILog;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity<TestVH> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main)


        ILog.i("asdl;fasl;d'fkasdfasdadsfasdfkas;'dlkf'asl;dk");

    }


    @Override
    public void initDatas() {

    }

    @Override
    protected void initEvents() {

    }

    @Nullable
    @Override
    protected BaseActivityDelegate initDelegate() {
        return null;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.base_activity_preview_pictures;
    }
}
