package com.yuanshenbin.basic.config;

import android.content.Context;

import com.tencent.mmkv.MMKV;
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.util.SPProxy;
import com.yuanshenbin.basic.util.SPUtils;

/**
 * author : yuanshenbin
 * time   : 2021-01-25
 * desc   : 一些基础配置
 */
public class BasicOptions {
    private int pageSize = 20;//分页默认值
    private static BasicOptions instance;
    private Context mContext;
    private IImageLoaderProxy mIImageLoaderProxy;

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

    public BasicOptions init(Context context) {
        this.mContext = context;
        return this;
    }


    public BasicOptions tipsDialogConfig(TipsAbstract tipsAbstract) {
        mTipsAbstract = tipsAbstract;
        return this;
    }

    public BasicOptions imageLoaderProxy(IImageLoaderProxy iImageLoaderProxy) {
        ImageLoader.getInstance()
                .setImageLoaderProxy(iImageLoaderProxy);
        return this;
    }

    public void build() {
        if (mIImageLoaderProxy != null) {
            ImageLoader.getInstance()
                    .setImageLoaderProxy(mIImageLoaderProxy);
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
}
