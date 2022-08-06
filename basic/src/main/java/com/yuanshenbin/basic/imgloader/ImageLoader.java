package com.yuanshenbin.basic.imgloader;

import android.widget.ImageView;

/**
 * author : yuanshenbin
 * time   : 2021-09-24
 * desc   : 抽象的图片加载期
 */
public class ImageLoader {
    private static ImageLoader manager = null;
    private IImageLoaderProxy mIImageLoaderProxy;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (manager == null) {
            synchronized (ImageLoader.class) {
                if (manager == null) {
                    manager = new ImageLoader();
                }
            }
        }
        return manager;
    }

    public void setImageLoaderProxy(IImageLoaderProxy IImageLoaderProxy) {
        this.mIImageLoaderProxy = IImageLoaderProxy;
    }

    public void displayImage(Object url, ImageView view) {
        mIImageLoaderProxy.displayImage(url, view);
    }

    public ImageLoader placeholder(int resId) {
        mIImageLoaderProxy.placeholder(resId);
        return this;
    }

    public ImageLoader options(ImageOptions l) {
        mIImageLoaderProxy.options(l);
        return this;
    }

    public long getCacheSize() {
        return mIImageLoaderProxy.getCacheSize();
    }

    public void clearCache() {
        mIImageLoaderProxy.clearCache();
    }
}
