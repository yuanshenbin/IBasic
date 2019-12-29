package com.yuanshenbin.basic.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * author : yuanshenbin
 * time   : 2018/8/16
 * desc   :
 */

public class StacksManager {

    /**
     * 强引用使用不当（添加后没移除）会导致activiy内存溢出，反复切换activity会导致列表中的activiy数量暴增，内存消耗变大，
     * 如果要用强引用推荐在每一个activit的onCreate中添加，onDestory中移除，或者所有activity继承同一个Activity，并在onCreate onDestroy中添加删除
     * LeakCanary检测到内存泄漏，
     * StrictMode也有警告
     */
    private final static String DEFAULT = "default";

    private Stack<WeakReference<Activity>> activityStack = new Stack<WeakReference<Activity>>();


    private Map<String, Stack<WeakReference<Activity>>> mStackMaps = new HashMap<>();
    private static StacksManager instance;

    private StacksManager() {
        mStackMaps.put(DEFAULT, new Stack<WeakReference<Activity>>());

    }

    /**
     * 单一实例
     */
    public static StacksManager getInstance() {
        if (instance == null) {
            instance = new StacksManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            Iterator<WeakReference<Activity>> it = mStackMaps.get(DEFAULT).iterator();
            while (it.hasNext()) {
                WeakReference<Activity> weakReference = it.next();
                if (weakReference != null && weakReference.get() == activity) {
                    return;
                }
            }
            mStackMaps.get(DEFAULT).add(new WeakReference<Activity>(activity));
        }

    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity, String flag) {
        if (activity != null) {
            if (mStackMaps.get(flag) == null) {
                mStackMaps.put(flag, new Stack<WeakReference<Activity>>());
            }
            Iterator<WeakReference<Activity>> it = mStackMaps.get(flag).iterator();
            while (it.hasNext()) {
                WeakReference<Activity> weakReference = it.next();
                if (weakReference != null && weakReference.get() == activity) {
                    return;
                }
            }
            mStackMaps.get(flag).add(new WeakReference<Activity>(activity));
        }

    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            Iterator<WeakReference<Activity>> it = mStackMaps.get(DEFAULT).iterator();
            while (it.hasNext()) {
                WeakReference<Activity> weakReference = it.next();
                if (weakReference != null && activity == weakReference.get()) {
                    it.remove();
                    weakReference.get().finish();
                    return;
                }
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {

        Iterator<WeakReference<Activity>> it = mStackMaps.get(DEFAULT).iterator();
        while (it.hasNext()) {
            WeakReference<Activity> weakReference = it.next();
            if (weakReference != null && weakReference.get() != null && weakReference.get().getClass() == cls) {
                it.remove();
                weakReference.get().finish();
                return;
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls, String flag) {
        if (mStackMaps.get(flag) == null) {
            return;
        }
        Iterator<WeakReference<Activity>> it = mStackMaps.get(flag).iterator();
        while (it.hasNext()) {
            WeakReference<Activity> weakReference = it.next();
            if (weakReference != null && weakReference.get() != null && weakReference.get().getClass() == cls) {
                it.remove();
                weakReference.get().finish();
                return;
            }
        }
    }

    /**
     * 结束指定类名的Activity,保留指定的activity
     */
    public void finishActivity(Class<?> cls, Activity keeyactivity) {

        Iterator<WeakReference<Activity>> it = activityStack.iterator();
        while (it.hasNext()) {
            WeakReference<Activity> weakReference = it.next();
            if (weakReference != null && weakReference.get() != null && keeyactivity != weakReference.get() && weakReference.get().getClass() == cls) {
                it.remove();
                weakReference.get().finish();
                return;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        Set<String> set = mStackMaps.keySet();
        for (String s : set) {
            Iterator<WeakReference<Activity>> it = mStackMaps.get(s).iterator();
            while (it.hasNext()) {
                WeakReference<Activity> weakReference = it.next();
                if (weakReference != null && weakReference.get() != null) {
                    it.remove();
                    weakReference.get().finish();

                }
            }
        }

    }

    public void finishAllActivity(String flag) {
        if (mStackMaps.get(flag) == null) {
            return;
        }
        Iterator<WeakReference<Activity>> it = mStackMaps.get(flag).iterator();
        while (it.hasNext()) {
            WeakReference<Activity> weakReference = it.next();
            if (weakReference != null && weakReference.get() != null) {
                it.remove();
                weakReference.get().finish();
            }
        }
    }

}