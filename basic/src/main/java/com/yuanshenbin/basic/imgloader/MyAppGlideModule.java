//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuanshenbin.basic.imgloader;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;

public class MyAppGlideModule extends AppGlideModule {
    public MyAppGlideModule() {
    }

    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    public boolean isManifestParsingEnabled() {
        return false;
    }
}
