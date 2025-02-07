package com.yuanshenbin;

import android.app.Application;
import android.content.Context;

import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.config.TipsAbstract;
import com.yuanshenbin.basic.develop.DevelopConfig;


/**
 * author : yuanshenbin
 * time   : 2022-01-27
 * desc   :
 */
public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        BasicOptions
                .getInstance()
                .init(this, true)
                .developConfig(new DevelopConfig.Builder(mContext).build())
                .tipsDialogConfig(new TipsAbstract() {
                }).build();


    }
    public static Context getContext() {

        return mContext;
    }
}
