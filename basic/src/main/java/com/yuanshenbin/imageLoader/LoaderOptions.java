package com.yuanshenbin.imageLoader;

/**
 * author : yuanshenbin
 * time   : 2018/7/18
 * desc   :
 */

public class LoaderOptions {
    private int resourceId;//展位图

    private int cacheSize = 20 * 1024;//缓存大小

    private IImageLoader imageLoader;

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public IImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(IImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }
}
