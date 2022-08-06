package com.yuanshenbin;

import android.app.Application;

import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.config.TipsAbstract;

/**
 * author : yuanshenbin
 * time   : 2022-01-27
 * desc   :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BasicOptions.Companion
                .getInstance()
                .tipsDialogConfig(new TipsAbstract() {
                }).build();
    }
}
