package com.yuanshenbin.basic.startup.manager;


import com.yuanshenbin.basic.startup.Result;
import com.yuanshenbin.basic.startup.Startup;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   :  第一次启动app后，如果出现隐私协议信任后，
 * 去登录页面，如果登录页面有例如：闪验sdk这种耗时初始化的操作，需要手动改成main线程执行，否则依然放在ui线程，会出现未初始化的一场
 */
public class StartupCacheConvertManager {

    //用于缓存每个1任务执行结果  result用来给ConcurrentHashMap防止为null

    private ConcurrentHashMap<Class<? extends Startup>, Result> mInitializedConvertComponents = new ConcurrentHashMap<>();
    private static StartupCacheConvertManager mInstance;

    private StartupCacheConvertManager() {

    }

    public static StartupCacheConvertManager getInstance() {
        if (mInstance == null) {
            synchronized (StartupCacheConvertManager.class) {
                if (mInstance == null) {
                    mInstance = new StartupCacheConvertManager();
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
        mInitializedConvertComponents.put(zClass, result);
    }

    /**
     * check initialized
     *
     * @param zClass
     * @return
     */
    public boolean hadInitialized(Class<? extends Startup> zClass) {
        return mInitializedConvertComponents.containsKey(zClass);
    }

    public <T> Result<T> obtainInitializedResult(Class<? extends Startup<T>> zClass) {
        return mInitializedConvertComponents.get(zClass);
    }

    public void clear() {
        mInitializedConvertComponents.clear();
    }

    public int size() {
        return mInitializedConvertComponents.size();
    }



}
