package com.yuanshenbin.basic.state

import androidx.annotation.DrawableRes

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 空数据状态
 */
abstract class EmptyState : StateAbstract() {
    protected var listener: OnRetryListener? = null
    abstract fun showEmpty(@DrawableRes resId: Int, vararg msg: String?)
    abstract fun showEmpty(@DrawableRes resId: Int)
    abstract fun showEmpty(vararg msg: CharSequence?)
}