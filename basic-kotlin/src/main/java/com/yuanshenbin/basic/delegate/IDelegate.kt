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
    fun onShowToast(context: Context?, str: String?) {}
    fun onStateLayout(model: ResponseModel?, layoutManager: StateLayoutManager?) {
        if (layoutManager != null && model != null) {
            when (model.state) {
                ResponseEnum.开始 -> layoutManager.showLoading()
                ResponseEnum.成功 -> layoutManager.showContent()
                ResponseEnum.结束 -> {
                }
                ResponseEnum.失败 -> if (NetworkUtils.isNetworkConnected(layoutManager.context)) {
                    layoutManager.networkState.showNetwork("网络似乎开小差了", "重试")
                } else {
                    layoutManager.showNetwork()
                }
                else -> {
                }
            }
        }
    }

    fun onListDataEmpty(pullAndPush: Boolean, result: Any?): Boolean {
        return false
    }

    fun handleListData(adapter: CommonAdapter<*>?, list: List<*>?, result: Any?, isPullAndPush: Boolean, loading: Int, page: Int, size: Int, emptyMsg: CharSequence?) {}
    fun onTitle(title: String?) {}
    fun onStart() {}
    fun onStop() {}
    fun onResume() {}
    fun onPause() {}
    fun onReload() {}
}