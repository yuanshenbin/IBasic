//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;

public final class GlideRequests extends RequestManager {
    public GlideRequests(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
        super(glide, lifecycle, treeNode);
    }

    public <ResourceType> GlideRequest<ResourceType> as(Class<ResourceType> resourceClass) {
        return new GlideRequest(this.glide, this, resourceClass);
    }

    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest)super.asBitmap();
    }

    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest)super.asGif();
    }

    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest)super.asDrawable();
    }

    public GlideRequest<Drawable> load(Object arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<File> downloadOnly() {
        return (GlideRequest)super.downloadOnly();
    }

    public GlideRequest<File> download(Object arg0) {
        return (GlideRequest)super.download(arg0);
    }

    public GlideRequest<File> asFile() {
        return (GlideRequest)super.asFile();
    }

    protected void setRequestOptions(@NonNull RequestOptions toSet) {
        if (toSet instanceof GlideOptions) {
            super.setRequestOptions(toSet);
        } else {
            super.setRequestOptions((new GlideOptions()).apply(toSet));
        }

    }
}
