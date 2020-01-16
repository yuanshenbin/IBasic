//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URL;

public  class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> {
    GlideRequest(Class<TranscodeType> transcodeClass, RequestBuilder<?> other) {
        super(transcodeClass, other);
    }

    GlideRequest(Glide glide, RequestManager requestManager, Class<TranscodeType> transcodeClass) {
        super(glide, requestManager, transcodeClass);
    }

    protected GlideRequest<File> getDownloadOnlyRequest() {
        return (new GlideRequest(File.class, this)).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    public GlideRequest<TranscodeType> sizeMultiplier(float sizeMultiplier) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).sizeMultiplier(sizeMultiplier);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).sizeMultiplier(sizeMultiplier);
        }

        return this;
    }

    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean flag) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).useUnlimitedSourceGeneratorsPool(flag);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).useUnlimitedSourceGeneratorsPool(flag);
        }

        return this;
    }

    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean flag) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).onlyRetrieveFromCache(flag);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).onlyRetrieveFromCache(flag);
        }

        return this;
    }

    public GlideRequest<TranscodeType> diskCacheStrategy(DiskCacheStrategy arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).diskCacheStrategy(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).diskCacheStrategy(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> priority(Priority arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).priority(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).priority(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> placeholder(Drawable arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).placeholder(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).placeholder(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> placeholder(int resourceId) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).placeholder(resourceId);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).placeholder(resourceId);
        }

        return this;
    }

    public GlideRequest<TranscodeType> fallback(Drawable drawable) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fallback(drawable);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fallback(drawable);
        }

        return this;
    }

    public GlideRequest<TranscodeType> fallback(int resourceId) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fallback(resourceId);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fallback(resourceId);
        }

        return this;
    }

    public GlideRequest<TranscodeType> error(Drawable arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).error(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).error(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> error(int resourceId) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).error(resourceId);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).error(resourceId);
        }

        return this;
    }

    public GlideRequest<TranscodeType> theme(Theme theme) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).theme(theme);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).theme(theme);
        }

        return this;
    }

    public GlideRequest<TranscodeType> skipMemoryCache(boolean skip) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).skipMemoryCache(skip);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).skipMemoryCache(skip);
        }

        return this;
    }

    public GlideRequest<TranscodeType> override(int width, int height) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).override(width, height);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).override(width, height);
        }

        return this;
    }

    public GlideRequest<TranscodeType> override(int size) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).override(size);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).override(size);
        }

        return this;
    }

    public GlideRequest<TranscodeType> signature(Key arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).signature(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).signature(arg0);
        }

        return this;
    }

    public <T> GlideRequest<TranscodeType> set(Option<T> arg0, T arg1) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).set(arg0, arg1);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).set(arg0, arg1);
        }

        return this;
    }

    public GlideRequest<TranscodeType> decode(Class<?> arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).decode(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).decode(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> encodeFormat(CompressFormat arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).encodeFormat(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).encodeFormat(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> encodeQuality(int quality) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).encodeQuality(quality);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).encodeQuality(quality);
        }

        return this;
    }

    public GlideRequest<TranscodeType> format(DecodeFormat arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).format(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).format(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> frame(long frameTimeMicros) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).frame(frameTimeMicros);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).frame(frameTimeMicros);
        }

        return this;
    }

    public GlideRequest<TranscodeType> downsample(DownsampleStrategy arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).downsample(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).downsample(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalCenterCrop() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCenterCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCenterCrop();
        }

        return this;
    }

    public GlideRequest<TranscodeType> centerCrop(Context context) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).centerCrop(context);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).centerCrop(context);
        }

        return this;
    }

    public GlideRequest<TranscodeType> centerCrop() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).centerCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).centerCrop();
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalFitCenter() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalFitCenter();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalFitCenter();
        }

        return this;
    }

    public GlideRequest<TranscodeType> fitCenter() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).fitCenter();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).fitCenter();
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalCenterInside(Context context) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCenterInside(context);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCenterInside(context);
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalCenterInside() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCenterInside();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCenterInside();
        }

        return this;
    }

    public GlideRequest<TranscodeType> centerInside() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).centerInside();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).centerInside();
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalCircleCrop() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalCircleCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalCircleCrop();
        }

        return this;
    }

    public GlideRequest<TranscodeType> circleCrop(Context context) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).circleCrop(context);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).circleCrop(context);
        }

        return this;
    }

    public GlideRequest<TranscodeType> circleCrop() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).circleCrop();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).circleCrop();
        }

        return this;
    }

    public GlideRequest<TranscodeType> transform(Transformation<Bitmap> arg0) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).transform(arg0);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).transform(arg0);
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalTransform(Context context, Transformation<Bitmap> transformation) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalTransform(context, transformation);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalTransform(context, transformation);
        }

        return this;
    }

    public GlideRequest<TranscodeType> optionalTransform(Transformation<Bitmap> transformation) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalTransform(transformation);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalTransform(transformation);
        }

        return this;
    }

    public <T> GlideRequest<TranscodeType> optionalTransform(Class<T> resourceClass, Transformation<T> transformation) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).optionalTransform(resourceClass, transformation);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).optionalTransform(resourceClass, transformation);
        }

        return this;
    }

    public <T> GlideRequest<TranscodeType> transform(Class<T> resourceClass, Transformation<T> transformation) {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).transform(resourceClass, transformation);
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).transform(resourceClass, transformation);
        }

        return this;
    }

    public GlideRequest<TranscodeType> dontTransform() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).dontTransform();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).dontTransform();
        }

        return this;
    }

    public GlideRequest<TranscodeType> dontAnimate() {
        if (this.getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions)this.getMutableOptions()).dontAnimate();
        } else {
            this.requestOptions = (new GlideOptions()).apply(this.requestOptions).dontAnimate();
        }

        return this;
    }

    public GlideRequest<TranscodeType> apply(RequestOptions arg0) {
        return (GlideRequest)super.apply(arg0);
    }

    public GlideRequest<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> arg0) {
        return (GlideRequest)super.transition(arg0);
    }

    public GlideRequest<TranscodeType> listener(RequestListener<TranscodeType> arg0) {
        return (GlideRequest)super.listener(arg0);
    }

    public GlideRequest<TranscodeType> thumbnail(RequestBuilder<TranscodeType> arg0) {
        return (GlideRequest)super.thumbnail(arg0);
    }

    public GlideRequest<TranscodeType> thumbnail(float sizeMultiplier) {
        return (GlideRequest)super.thumbnail(sizeMultiplier);
    }

    public GlideRequest<TranscodeType> load(Object arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(String arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(Uri arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(File arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(Integer arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(URL arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(byte[] arg0) {
        return (GlideRequest)super.load(arg0);
    }

    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest)super.clone();
    }
}
