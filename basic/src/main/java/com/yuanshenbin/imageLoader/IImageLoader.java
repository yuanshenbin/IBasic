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

interface IImageLoader {

    void displayImage(Object path, ImageView imageView, int placeholder, Transformation<Bitmap> transformation);

    void displayImage(Object path, ImageView imageView, int placeholder);

    void clearDiskCache(Context context);

    void clearMemoryCache(Context context);

    String cacheSize(File file);
}
