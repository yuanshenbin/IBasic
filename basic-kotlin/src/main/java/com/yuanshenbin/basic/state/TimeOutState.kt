package com.yuanshenbin.basic.state

import androidx.annotation.DrawableRes

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 超时状态
 */
abstract class TimeOutState : StateAbstract() {
    abstract fun showTimeOut(@DrawableRes resId: Int, vararg msg: String?)
    abstract fun showTimeOut(@DrawableRes resId: Int)
    abstract fun showTimeOut(vararg msg: String?)
}