package com.yuanshenbin.basic.config;

import android.content.Context;

/**
 * author : yuanshenbin
 * time   : 2021-01-25
 * desc   : 一些基础配置
 */
public class BasicConfig {

    public static int PAGE_SIZE = 20;//分页默认值
    private static BasicConfig instance;
    private Context mContext;

    private TipsAbstract mTipsAbstract = new TipsAbstract() {
    };
    private MMKVAbstract mMMKVAbstract = new MMKVAbstract() {
    };
    public static BasicConfig getInstance() {

        if (instance == null)
            synchronized (BasicConfig.class) {
                instance = new BasicConfig();
            }
        return instance;
    }

    public BasicConfig init(Context context) {
        this.mContext = context;
        return this;
    }

    public MMKVAbstract getMMKVAbstract() {
        return mMMKVAbstract;
    }

    public BasicConfig MMKVConfig(MMKVAbstract MMKVAbstract) {
        mMMKVAbstract = MMKVAbstract;
        return this;
    }

    public BasicConfig tipsDialogConfig(TipsAbstract tipsAbstract) {
        mTipsAbstract = tipsAbstract;
        return this;
    }

    public TipsAbstract getTipsAbstract() {
        return mTipsAbstract;
    }

    public Context getContext() {
        return mContext;
    }

}
