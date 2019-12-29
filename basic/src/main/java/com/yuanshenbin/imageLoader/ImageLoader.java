package com.yuanshenbin.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;

import java.io.File;

/**
 * author : yuanshenbin
 * time   : 2018/7/18
 * desc   :
 */

public class ImageLoader {

    private static ImageLoader mInstance;
    private int mResourceId = 0;

    private static LoaderOptions mOptions;
    private Transformation<Bitmap> transformation;

    public static ImageLoader getInstance() {
        if (mInstance == null)
            synchronized (ImageLoader.class) {
                mInstance = new ImageLoader();
            }
        return mInstance;
    }

    public ImageLoader placeholder(int resourceId) {
        this.mResourceId = resourceId;
        return this;
    }

    public ImageLoader transformation(Transformation<Bitmap> transformation) {
        this.transformation = transformation;
        return this;
    }

    public void displayImage(Object path, ImageView imageView) {
        if (transformation != null) {
            mOptions.getImageLoader().displayImage(path, imageView, mResourceId, transformation);
        } else {
            mOptions.getImageLoader().displayImage(path, imageView, mResourceId);
        }
        mResourceId = 0;
        transformation = null;
    }

    public LoaderOptions getOptions() {
        return mOptions;
    }

    public void Initialization(LoaderOptions options) {
        mOptions = options;
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public void clearMemoryCache(Context context) {
        mOptions.getImageLoader().clearMemoryCache(context);
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public void clearDiskCache(Context context) {
        mOptions.getImageLoader().clearDiskCache(context);
    }

    /**
     * 获取磁盘大小
     *
     * @param file
     */
    public String getCacheSize(File file) {
        return mOptions.getImageLoader().cacheSize(file);
    }
}
