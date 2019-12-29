package com.yuanshenbin.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * author : yuanshenbin
 * time   : 2018/7/18
 * desc   :
 */

public class GlideImageLoader implements IImageLoader {


    @Override
    public void displayImage(Object path, ImageView imageView, int placeholder) {

        RequestOptions requestOptions;
        if (placeholder == 0) {
            requestOptions = new RequestOptions()
                    .placeholder(ImageLoader.getInstance().getOptions().getResourceId())
                    .error(ImageLoader.getInstance().getOptions().getResourceId())
                    .fallback(ImageLoader.getInstance().getOptions().getResourceId())
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            requestOptions = new RequestOptions()
                    .placeholder(placeholder);
        }
        Glide.with(imageView.getContext())
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void displayImage(Object path, ImageView imageView, int placeholder, Transformation<Bitmap> transformation) {

        RequestOptions requestOptions;
        if (placeholder == 0) {
            requestOptions = new RequestOptions()
                    .placeholder(ImageLoader.getInstance().getOptions().getResourceId())
                    .error(ImageLoader.getInstance().getOptions().getResourceId())
                    .fallback(ImageLoader.getInstance().getOptions().getResourceId())
                    .bitmapTransform(transformation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            requestOptions = new RequestOptions()
                    .bitmapTransform(transformation)
                    .placeholder(placeholder);
        }
        Glide.with(imageView.getContext())
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void clearDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String cacheSize(File file) {
        try {
            return ImageUtils.getCacheSize(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
