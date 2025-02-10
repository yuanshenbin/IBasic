package com.yuanshenbin.basic.startup.manager;


import com.yuanshenbin.basic.startup.Result;
import com.yuanshenbin.basic.startup.Startup;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   :  缓存执行完成的任务结果
 */
public class StartupCacheManager {

    //用于缓存每个1任务执行结果  result用来给ConcurrentHashMap防止为null
    private ConcurrentHashMap<Class<? extends Startup>, Result> mInitializedComponents = new ConcurrentHashMap<>();
    private static StartupCacheManager mInstance;

    private StartupCacheManager() {

    }

    public static StartupCacheManager getInstance() {
        if (mInstance == null) {
            synchronized (StartupCacheManager.class) {
                if (mInstance == null) {
                    mInstance = new StartupCacheManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * save result of initialized Component
     *
     * @param zClass
     * @param result
     */
    public void saveInitializedComponent(Class<? extends Startup> zClass, Result result) {
        mInitializedComponents.put(zClass, result);
    }

    /**
     * check initialized
     *
     * @param zClass
     * @return
     */
    public boolean hadInitialized(Class<? extends Startup> zClass) {
        return mInitializedComponents.containsKey(zClass);
    }

    public <T> Result<T> obtainInitializedResult(Class<? extends Startup<T>> zClass) {
        return mInitializedComponents.get(zClass);
    }

    public void clear() {
        mInitializedComponents.clear();
    }

    public int size() {
        return mInitializedComponents.size();
    }
}
