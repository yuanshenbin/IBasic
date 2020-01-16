//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;


public class ImageLoader {
    private static volatile ImageLoader manager = null;
    private ILoadProxyInterface iLoadProxyInterface;
    private int mResourceId =0;
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

    public ImageLoader setILoadProxyInterface(ILoadProxyInterface iLoadProxyInterface) {
        this.iLoadProxyInterface = iLoadProxyInterface;
        return this;
    }

    public ImageLoader setLoadConfig(LoaderOptions options) {
        this.iLoadProxyInterface.setLoaderOptions(options);
        return this;
    }

    public void displayImage(Object url, ImageView view) {
        this.iLoadProxyInterface.loadImage(view, url,mResourceId);
        mResourceId =0;
        return;
    }
    public ImageLoader placeholder(int placeholder) {
        this.mResourceId = placeholder;
        return this;
    }

    public void asynchronousDownloadBitmap(SimpleTarget<Bitmap> requestListener, String imgUrl) {
        this.iLoadProxyInterface.asynchronousDownloadBitmap(requestListener, imgUrl);
    }

    public void clearMemoryCache() {
        this.iLoadProxyInterface.clearMemoryCache();
    }

    public void clearDiskCache() {
        this.iLoadProxyInterface.clearDiskCache();
    }

    public void pause() {
        this.iLoadProxyInterface.pause();
    }

    public void resume() {
        this.iLoadProxyInterface.resume();
    }
}