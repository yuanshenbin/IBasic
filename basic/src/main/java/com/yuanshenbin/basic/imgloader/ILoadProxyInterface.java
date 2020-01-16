//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.request.target.SimpleTarget;

public interface ILoadProxyInterface {
    void loadImage(ImageView var1, Object var2);

    void setLoaderOptions(LoaderOptions var1);

    void clearMemoryCache();

    void clearDiskCache();

    void pause();

    void resume();

    void asynchronousDownloadBitmap(SimpleTarget<Bitmap> var1, String var2);
}
