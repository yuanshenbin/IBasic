package com.yuanshenbin.basic.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference
import java.util.*

/**
 * author : yuanshenbin
 * time   : 2018/8/16
 * desc   :
 */
class StacksManager {
    private val activityStack = Stack<WeakReference<Activity?>>()
    private val mStackMaps: MutableMap<String, Stack<WeakReference<Activity?>>?> = HashMap()

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activity != null) {
            val it: Iterator<WeakReference<Activity?>> = mStackMaps[DEFAULT]!!.iterator()
            while (it.hasNext()) {
                val weakReference = it.next()
                if (weakReference != null && weakReference.get() === activity) {
                    return
                }
            }
            mStackMaps[DEFAULT]!!.add(WeakReference(activity))
        }
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?, flag: String) {
        if (activity != null) {
            if (mStackMaps[flag] == null) {
                mStackMaps[flag] = Stack()
            }
            val it: Iterator<WeakReference<Activity?>> = mStackMaps[flag]!!.iterator()
            while (it.hasNext()) {
                val weakReference = it.next()
                if (weakReference?.get() === activity) {
                    return
                }
            }
            mStackMaps[flag]!!.add(WeakReference(activity))
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            val it = mStackMaps[DEFAULT]!!.iterator()
            while (it.hasNext()) {
                val weakReference = it.next()
                if (activity === weakReference?.get()) {
                    it.remove()
                    weakReference.get()?.finish()
                    return
                }
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        val it = mStackMaps[DEFAULT]!!.iterator()
        while (it.hasNext()) {
            val weakReference = it.next()
            if (weakReference?.get()?.javaClass == cls) {
                it.remove()
                weakReference.get()?.finish()
                return
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>, flag: String) {
        if (mStackMaps[flag] == null) {
            return
        }
        val it = mStackMaps[flag]!!.iterator()
        while (it.hasNext()) {
            val weakReference = it.next()
            if (weakReference?.get() != null && weakReference.get()!!.javaClass == cls) {
                it.remove()
                weakReference.get()?.finish()
                return
            }
        }
    }

    /**
     * 结束指定类名的Activity,保留指定的activity
     */
    fun finishActivity(cls: Class<*>, keeyactivity: Activity) {
        val it = activityStack.iterator()
        while (it.hasNext()) {
            val weakReference = it.next()
            if (weakReference?.get() != null && keeyactivity !== weakReference.get() && weakReference.get()!!.javaClass == cls) {
                it.remove()
                weakReference.get()?.finish()
                return
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        val set: Set<String> = mStackMaps.keys
        for (s in set) {
            val it = mStackMaps[s]!!.iterator()
            while (it.hasNext()) {
                val weakReference = it.next()
                if (weakReference?.get() != null) {
                    it.remove()
                    weakReference.get()!!.finish()
                }
            }
        }
    }

    fun finishAllActivity(flag: String) {
        if (mStackMaps[flag] == null) {
            return
        }
        val it = mStackMaps[flag]!!.iterator()
        while (it.hasNext()) {
            val weakReference = it.next()
            if (weakReference?.get() != null) {
                it.remove()
                weakReference.get()!!.finish()
            }
        }
    }

    /**
     * 检查activity是否在栈中
     * @param context
     * @param className 绝对路径的actiivty
     * @param maxNum 检查栈顶下面多说个activity
     * @return
     */
    fun isActivityStack(context: Context, className: String?, maxNum: Int): Boolean {
        var flag = false
        try {
            val intent = Intent()
            intent.setClassName(context.applicationContext, className!!)
            val cmpName = intent.resolveActivity(context.applicationContext.packageManager)
            if (cmpName != null) { // 说明系统中存在这个activity
                val am = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val taskInfoList = am.getRunningTasks(maxNum) //获取从栈顶开始往下查找的15个activity  
                for (taskInfo in taskInfoList) {
                    if (taskInfo.baseActivity == cmpName) { // 说明它已经启动了
                        flag = true
                        break //跳出循环，优化效率  
                    }
                }
            }
        } catch (e: Exception) {
            return flag
        }
        return flag
    }

    /**
     * @param context
     * @param className
     * @return
     */
    fun isActivityStack(context: Context, className: String?): Boolean {
        return isActivityStack(context, className, 15)
    }

    companion object {


        /**
         * 强引用使用不当（添加后没移除）会导致activiy内存溢出，反复切换activity会导致列表中的activiy数量暴增，内存消耗变大，
         * 如果要用强引用推荐在每一个activit的onCreate中添加，onDestory中移除，或者所有activity继承同一个Activity，并在onCreate onDestroy中添加删除
         * LeakCanary检测到内存泄漏，
         * StrictMode也有警告
         */
        private const val DEFAULT = "default"

        /**
         * 单一实例
         */
        @JvmStatic
        var instance: StacksManager? = null
            get() {
                if (field == null) {
                    field = StacksManager()
                }
                return field
            }
            private set


    }

    init {
        mStackMaps[DEFAULT] = Stack()
    }
}