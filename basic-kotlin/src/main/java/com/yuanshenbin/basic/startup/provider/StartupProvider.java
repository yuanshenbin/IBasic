package com.yuanshenbin.basic.startup.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.yuanshenbin.basic.startup.Startup;
import com.yuanshenbin.basic.startup.manager.StartupCacheConvertManager;
import com.yuanshenbin.basic.startup.manager.StartupCacheDelayManager;
import com.yuanshenbin.basic.startup.manager.StartupCacheManager;
import com.yuanshenbin.basic.startup.manager.StartupCostTimesManager;
import com.yuanshenbin.basic.startup.manager.StartupManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author : yuanshenbin
 * time   : 2023/1/5
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public  class StartupProvider  extends ContentProvider {
    @Override
    public boolean onCreate() {
        StartupCostTimesManager.getInstance().clear();
        StartupCacheManager.getInstance().clear();
        StartupCacheConvertManager.getInstance().clear();
        StartupCacheDelayManager.getInstance().clear();
        try {
            //初始化任务
            List<Startup<?>> startups =StartupInitializer.
                    discoverAndInitialize(getContext(),getClass().getName());
                    //执行任务
            new StartupManager.Builder()
                    .addAllStartup(startups)
                    .build(getContext())
                    .start().await();
        }catch ( Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
