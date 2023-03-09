package com.yuanshenbin.basic.startup.provider;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;

import com.yuanshenbin.basic.startup.Startup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * author : yuanshenbin
 * time   : 2023/1/5
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public class StartupInitializer {

    public static String META_VALUE = "android.startup";

    public static List<Startup<?>> discoverAndInitialize(Context context, String provideName) throws Exception {
        //用爱保存有序无环图 任务的结构图
        Map<Class<? extends Startup>, Startup<?>> startups = new HashMap<>();
        //获得manifest contentProvider 中的 meta-data PMS
        ComponentName provider = new ComponentName(context, provideName);
        ProviderInfo providerInfo = context.getPackageManager().getProviderInfo(provider, PackageManager.GET_META_DATA);
        //得到manifest中的配置任务
        for (String key : providerInfo.metaData.keySet()) {
            String value = providerInfo.metaData.getString(key);
            if (TextUtils.equals(META_VALUE, value)) {
                Class<?> clazz = Class.forName(key);//xxx.task
                if (Startup.class.isAssignableFrom(clazz)) {
                    doInitialize((Startup<?>) clazz.newInstance(), startups);
                }
            }
        }

        List<Startup<?>> result = new ArrayList<>(startups.values());

        return result;
    }

    private static void doInitialize(Startup<?> startup, Map<Class<? extends Startup>, Startup<?>> startups) throws Exception {

        //避免重复 不能使用list
        startups.put(startup.getClass(), startup);
        if (startup.getDependenciesCount() != 0) {
            //遍历父任务
            for (Class<? extends Startup<?>> dependency : startup.dependencies()) {
                doInitialize(dependency.newInstance(), startups);
            }
        }
    }
}
