//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.content.Context;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class LoaderOptions {
    private int placeholderResId;
    private int errorResId;
    private int fallbackResId;
    private int reSizeW;
    private int reSizeH;
    private DiskCacheStrategy diskCacheStrategy;
    private int cacheSizeInM;
    private MemoryCategory memoryCategory;
    private Context context;
    private int asImternalcd;

    public LoaderOptions(Builder builder) {
        this.context = builder.context;
        this.fallbackResId = builder.fallbackResId;
        this.placeholderResId = builder.placeholderResId;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.errorResId = builder.errorResId;
        this.cacheSizeInM = builder.cacheSizeInM;
        this.memoryCategory = builder.memoryCategory;
        this.asImternalcd = builder.asImternalcd;
        this.reSizeW = builder.reSizeW;
        this.reSizeH = builder.reSizeH;
    }

    public int getReSizeW() {
        return this.reSizeW;
    }

    public int getReSizeH() {
        return this.reSizeH;
    }

    public Context getContext() {
        return this.context;
    }

    public int getAsImternalcd() {
        return this.asImternalcd;
    }

    public int getPlaceholderResId() {
        return this.placeholderResId;
    }

    public int getErrorResId() {
        return this.errorResId;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public int getFallbackResId() {
        return this.fallbackResId;
    }

    public int getCacheSizeInM() {
        return this.cacheSizeInM;
    }

    public MemoryCategory getMemoryCategory() {
        return this.memoryCategory;
    }

    public static class Builder {
        private int placeholderResId;
        private int errorResId;
        private DiskCacheStrategy diskCacheStrategy;
        private int cacheSizeInM;
        private MemoryCategory memoryCategory;
        private int asImternalcd;
        private Context context;
        private int fallbackResId;
        private int reSizeW;
        private int reSizeH;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setReSizeW(int reSizeW) {
            this.reSizeW = reSizeW;
            return this;
        }

        public Builder setReSizeH(int reSizeH) {
            this.reSizeH = reSizeH;
            return this;
        }

        public Builder setFallbackResId(int fallbackResId) {
            this.fallbackResId = fallbackResId;
            return this;
        }

        public Builder setPlaceHolderResId(int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        public Builder setErrorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Builder setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder setCacheSizeInM(int cacheSizeInM) {
            this.cacheSizeInM = cacheSizeInM;
            return this;
        }

        public Builder setMemoryCategory(MemoryCategory memoryCategory) {
            this.memoryCategory = memoryCategory;
            return this;
        }

        public Builder setAsImternalcd(int asImternalcd) {
            this.asImternalcd = asImternalcd;
            return this;
        }

        public LoaderOptions build() {
            return new LoaderOptions(this);
        }
    }
}
