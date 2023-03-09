package com.yuanshenbin.basic.startup.manager;


import com.yuanshenbin.basic.startup.Result;
import com.yuanshenbin.basic.startup.Startup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   :  缓存执行完成的任务结果
 */
public class StartupCacheDelayManager {

    //用于缓存每个1任务执行结果  result用来给ConcurrentHashMap防止为null
    private ConcurrentHashMap<Startup<?>, Result> mInitializedComponents = new ConcurrentHashMap<>();
    private static StartupCacheDelayManager mInstance;

    private StartupCacheDelayManager() {

    }

    public static StartupCacheDelayManager getInstance() {
        if (mInstance == null) {
            synchronized (StartupCacheDelayManager.class) {
                if (mInstance == null) {
                    mInstance = new StartupCacheDelayManager();
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
    public void saveInitializedComponent(Startup<?> zClass, Result result) {
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

    public ConcurrentHashMap<Startup<?>, Result> getComponentList() {
        return mInitializedComponents;
    }

    public List<Startup<?>> removeProcessedInitializationReturnList(Class<? extends Startup>... clz) {
        Iterator<Startup<?>> iterator = getComponentList().keySet().iterator();
        List<Startup<?>> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Startup<?> startup = iterator.next();
            list.add(startup);
            for (int i = startup.dependencies().size() - 1; i >= 0; i--) {
                //这一步的目的是，因为部分实际已经初始化了，所以要移除task里面这个任务，避免重复初始化
                for (Class<? extends Startup> aClass : clz) {
                    if (aClass.isAssignableFrom(startup.dependencies().get(i))) {
                        startup.removeDependencies(startup.dependencies().get(i));
                    }
                }
            }
        }
        return list;
    }
}
