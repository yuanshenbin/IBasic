package com.yuanshenbin.basic.state

import androidx.annotation.DrawableRes

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 错误状态
 */
abstract class ErrorState : StateAbstract() {
    abstract fun showError(@DrawableRes resId: Int, vararg msg: String?)
    abstract fun showError(@DrawableRes resId: Int)
    abstract fun showError(vararg msg: String?)
}