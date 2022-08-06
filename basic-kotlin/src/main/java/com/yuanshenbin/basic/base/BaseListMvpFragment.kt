package com.yuanshenbin.basic.base

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.yuanshenbin.basic.adapter.CommonAdapter
import com.yuanshenbin.basic.constant.BasicConstants
import java.util.*

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */
abstract class BaseListMvpFragment<VH : BasicViewHolder?, Bean, V, P : BasePresenter<V>?> : BaseMvpFragment<VH, V, P>() {
    @JvmField
    var swipe_to_load_layout: SwipeToLoadLayout? = null

    @JvmField
    var mData: List<Bean?> = ArrayList()

    @JvmField
    var mAdapter: CommonAdapter<Bean?>? = null
    fun setFill(result: Any?, loading: Int, page: Int, size: Int, emptyMsg: CharSequence?) {
        swipe_to_load_layout!!.isRefreshing = false
        mDelegate!!.handleListData(mAdapter, mData, result, isPullAndPush, loading, page, size, emptyMsg)
    }

    fun setFill(result: Any?, loading: Int, size: Int) {
        setFill(result, loading, mPage, size, null)
    }

    fun setFill(result: Any?, loading: Int) {
        setFill(result, loading, BasicConstants.PAGESIZE)
    }

    fun setFill(result: Any?, loading: Int, empty: CharSequence?) {
        setFill(result, loading, mPage, BasicConstants.PAGESIZE, empty)
    }

    protected fun setListLoadFail() {
        swipe_to_load_layout!!.isRefreshing = false
        if (!isPullAndPush) {
            mPage--
            if (mAdapter!!.loadMoreModule != null) {
                mAdapter!!.loadMoreModule.loadMoreFail()
            }
        }
    }
}