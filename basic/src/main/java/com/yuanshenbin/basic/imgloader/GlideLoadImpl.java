//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

public class GlideLoadImpl implements ILoadProxyInterface {
    private Context ctx;
    private LoaderOptions options;

    public GlideLoadImpl() {
    }


    public void setLoaderOptions(LoaderOptions options) {
        this.ctx = options.getContext();
        this.options = options;
        GlideApp.get(this.ctx).setMemoryCategory(options.getMemoryCategory());
        RequestOptions requestOptions = new RequestOptions();
        RequestOptions.diskCacheStrategyOf(options.getDiskCacheStrategy());
        requestOptions.placeholder(options.getPlaceholderResId());
        requestOptions.error(options.getErrorResId());
        requestOptions.fallback(options.getFallbackResId());
        GlideApp.with(this.ctx).setDefaultRequestOptions(requestOptions);
    }

    public void clearMemoryCache() {
        GlideApp.get(this.ctx).clearMemory();
    }

    public void clearDiskCache() {
        GlideApp.get(this.ctx).clearDiskCache();
    }

    public void pause() {
        Glide.with(this.ctx).pauseRequests();
    }

    public void resume() {
        Glide.with(this.ctx).resumeRequests();
    }

    public void asynchronousDownloadBitmap(SimpleTarget<Bitmap> requestListener, String imgUrl) {
        if (requestListener == null) {
            requestListener = new SimpleTarget<Bitmap>() {
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                }
            };
        }

        GlideApp.with(this.ctx).asBitmap().load(imgUrl).into(requestListener);
    }

    @Override

    public void loadImage(ImageView view, Object path, int placeholder) {
        if (path == null) {
            this.loadImg(this.options.getPlaceholderResId(), view, placeholder);
        } else if (TextUtils.isEmpty(String.valueOf(path).trim())) {
            this.loadImg(this.options.getPlaceholderResId(), view, placeholder);
        } else if (path instanceof Integer) {
            this.loadImg(path, view, placeholder);
        } else if (String.valueOf(path).endsWith(".gif")) {
            this.loadGif(view, String.valueOf(path), placeholder);
        } else {
            this.hasImagCache(view, String.valueOf(path), placeholder);
        }
    }

    private void loadGif(final ImageView view, final String path, final int placeholder) {
        GlideApp.with(this.ctx)
                .asGif()
                .placeholder(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder)
                .error(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder)
                .fallback(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder)
                .load(path).onlyRetrieveFromCache(true).into(view);
    }

    private void hasWebpCache(final ImageView view, final String path, final int placeholder) {
        int index = path.lastIndexOf(".");
        String tails = path;
        if (index > 0) {
            tails = path.substring(0, index) + ".webp";
        }

        GlideApp.with(this.ctx).load(tails).onlyRetrieveFromCache(true).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                GlideLoadImpl.this.loadWebP(view, path, placeholder);
                return true;
            }

            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(view);
    }

    private void hasImagCache(final ImageView view, final String path, final int placeholder) {
        GlideApp.with(this.ctx).load(path).onlyRetrieveFromCache(true).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                GlideLoadImpl.this.hasWebpCache(view, path, placeholder);
                return true;
            }

            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(view);
    }

    private void loadWebP(final ImageView view, final String path, final int placeholder) {
        int index = path.lastIndexOf(".");
        String tails = path;
        if (index > 0) {
            tails = path.substring(0, index) + ".webp";
        }

        GlideApp.with(this.ctx).load(tails).placeholder(this.options.getPlaceholderResId()).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                GlideLoadImpl.this.loadImg(path, view, placeholder);
                return true;
            }

            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(view);
    }

    private void loadImg(Object url, ImageView view, int placeholder) {
        GlideApp.with(this.ctx).load(url).placeholder(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder)
                .error(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder)
                .fallback(placeholder == 0 ? this.options.getPlaceholderResId() : placeholder).into(view);

    }
}
