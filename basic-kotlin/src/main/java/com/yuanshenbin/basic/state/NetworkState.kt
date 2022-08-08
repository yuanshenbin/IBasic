package com.yuanshenbin.basic.state

import androidx.annotation.DrawableRes

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   : 网络异常状态
 */
abstract class NetworkState(open var listener: OnRetryListener?) : StateAbstract() {

    abstract fun showNetwork(@DrawableRes resId: Int, vararg msg: String?)
    abstract fun showNetwork(@DrawableRes resId: Int)
    abstract fun showNetwork(vararg msg: String?)

}