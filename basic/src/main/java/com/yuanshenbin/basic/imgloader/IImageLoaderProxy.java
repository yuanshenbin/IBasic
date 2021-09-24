package com.yuanshenbin.basic.imgloader;

import android.view.View;

/**
 * author : yuanshenbin
 * time   : 2021-09-24
 * desc   :
 */
public interface IImageLoaderProxy {

    void displayImage(Object url, View view);

    void placeholder(int resId);

    void options(ImageOptions l);
}
