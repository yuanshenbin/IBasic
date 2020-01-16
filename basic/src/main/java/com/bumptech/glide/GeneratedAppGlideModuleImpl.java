//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bumptech.glide;

import android.content.Context;
import android.util.Log;

import com.yuanshenbin.basic.imgloader.MyAppGlideModule;

import java.util.Collections;
import java.util.Set;

final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
    private final MyAppGlideModule appGlideModule = new MyAppGlideModule();

    GeneratedAppGlideModuleImpl() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: .MyAppGlideModule");
        }

    }

    public void applyOptions(Context context, GlideBuilder builder) {
        this.appGlideModule.applyOptions(context, builder);
    }

    public void registerComponents(Context context, Registry registry) {
        this.appGlideModule.registerComponents(context, registry);
    }

    public boolean isManifestParsingEnabled() {
        return this.appGlideModule.isManifestParsingEnabled();
    }

    public Set<Class<?>> getExcludedModuleClasses() {
        return Collections.emptySet();
    }

    GeneratedRequestManagerFactory getRequestManagerFactory() {
        return new GeneratedRequestManagerFactory();
    }
}
