package com.yuanshenbin.basic.config;

import android.content.Context;

import com.tencent.mmkv.MMKV;
import com.yuanshenbin.basic.call.ICatchConfig;
import com.yuanshenbin.basic.develop.DevelopConfig;
import com.yuanshenbin.basic.develop.DevelopMode;
import com.yuanshenbin.basic.develop.ui.ErrorDescActivity;
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.log.ILog;
import com.yuanshenbin.basic.log.LogAbstract;
import com.yuanshenbin.basic.log.LogImpl;
import com.yuanshenbin.basic.util.SPProxy;
import com.yuanshenbin.basic.util.SPUtils;

import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * author : yuanshenbin
 * time   : 2021-01-25
 * desc   : 一些基础配置
 */
public class BasicOptions {
    private int pageSize = 20;//分页默认值

    private int compressSize=100;
    private int compressCount=10;
    private static BasicOptions instance;
    private Context mContext;
    private IImageLoaderProxy mIImageLoaderProxy;
    private boolean debug;

    private DevelopConfig developConfig;
    private ICatchConfig catchConfig;

    private LogAbstract logAbstract = new LogImpl();


    private TipsAbstract mTipsAbstract = new TipsAbstract() {
    };
    private static MMKV mMMKV;
    private static SPProxy mSPProxy;


    public static BasicOptions getInstance() {

        if (instance == null)
            synchronized (BasicOptions.class) {
                instance = new BasicOptions();
            }
        return instance;
    }

    public BasicOptions init(Context context, boolean debug) {
        this.mContext = context;
        this.debug = debug;
        return this;
    }

    public BasicOptions spProxy(SPProxy spProxy) {
        this.mSPProxy = spProxy;
        return this;
    }

    public BasicOptions compressSize(int compressSize) {
        this.compressSize = compressSize;
        return this;
    }
    public BasicOptions compressCount(int compressCount) {
        this.compressCount = compressCount;
        return this;
    }


    public BasicOptions tipsDialogConfig(TipsAbstract tipsAbstract) {
        this.mTipsAbstract = tipsAbstract;
        return this;
    }

    public BasicOptions developConfig(DevelopConfig developConfig) {
        this.developConfig = developConfig;
        return this;
    }

    public BasicOptions logAbstract(LogAbstract logAbstract) {
        this.logAbstract = logAbstract;
        return this;
    }

    public BasicOptions catchConfig(ICatchConfig catchConfig) {
        this.catchConfig = catchConfig;
        return this;
    }


    public BasicOptions imageLoaderProxy(IImageLoaderProxy iImageLoaderProxy) {
        this.mIImageLoaderProxy = iImageLoaderProxy;
        return this;
    }

    public void build() {
        ILog.initialize(logAbstract);


        if (catchConfig != null) {


            catchConfig.config(new CaocConfig.Builder()
                    .errorActivity(ErrorDescActivity.class).trackActivities(true));
        } else {

            CaocConfig.Builder.create().errorActivity(ErrorDescActivity.class)
                    .trackActivities(true).apply();
        }

        if (mIImageLoaderProxy != null) {
            ImageLoader.getInstance()
                    .setImageLoaderProxy(mIImageLoaderProxy);
        }
        if (developConfig != null) {
            DevelopMode
                    .getInstance()
                    .InitializationConfig(developConfig);
        }
        if (mSPProxy != null) {
            SPUtils.initialize(mSPProxy);
        } else {
            mMMKV = MMKV.mmkvWithID(getContext().getPackageName());
            SPUtils.initialize(new SPProxy() {
                @Override
                public MMKV getSP(String key) {
                    return mMMKV;
                }
            });
        }
    }

    public TipsAbstract getTipsAbstract() {
        return mTipsAbstract;
    }

    public Context getContext() {
        return mContext;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void pageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCompressSize() {
        return compressSize;
    }

    public int getCompressCount() {
        return compressCount;
    }

    public boolean isDebug() {
        return debug;
    }
}
