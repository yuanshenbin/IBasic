package com.yuanshenbin.basic.delegate

import android.content.Context
import com.yuanshenbin.basic.adapter.CommonAdapter
import com.yuanshenbin.basic.state.StateLayoutManager
import com.yuanshenbin.basic.util.NetworkUtils
import com.yuanshenbin.network.ResponseEnum
import com.yuanshenbin.network.model.ResponseModel
import java.io.Serializable

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */
abstract class IDelegate : Serializable {
    open fun onShowToast(context: Context?, str: String?) {}
    open fun onStateLayout(model: ResponseModel?, layoutManager: StateLayoutManager?) {
        if (layoutManager != null && model != null) {
            when (model.state) {
                ResponseEnum.开始 -> layoutManager.showLoading()
                ResponseEnum.成功 -> layoutManager.showContent()
                ResponseEnum.结束 -> {
                }
                ResponseEnum.失败 -> if (NetworkUtils.isNetworkConnected(layoutManager.context)) {
                    layoutManager?.getNetworkState()?.showNetwork("网络似乎开小差了", "重试")
                } else {
                    layoutManager.showNetwork()


                }
                else -> {
                }
            }
        }
    }

    open fun onListDataEmpty(pullAndPush: Boolean, result: Any?): Boolean {
        return false
    }

    open fun handleListData(adapter: CommonAdapter<*>?, list: List<*>?, result: Any?, isPullAndPush: Boolean, loading: Int, page: Int, size: Int, emptyMsg: CharSequence?) {}
    open fun onTitle(title: String?) {}
    open fun onStart() {}
    open fun onStop() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onReload() {}
}