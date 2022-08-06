package com.yuanshenbin.basic.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * author : yuanshenbin
 * time   : 2017/9/27
 * desc   :
 */
abstract class CommonAdapter<T>(layoutResId: Int, data: List<T>?) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId, data as MutableList<T>?), LoadMoreModule {
    var l: OnMoreLoadListener? = null

    interface OnMoreLoadListener {
        fun onLoadMore()
    }

    fun setOnMoreLoadListener(l: OnMoreLoadListener?) {
        loadMoreModule.isEnableLoadMore = true
        this.l = l

    }

    /**
     * @param layoutResId
     * @param data
     */
    init {
        loadMoreModule.setOnLoadMoreListener {
            l?.onLoadMore()
        }
        loadMoreModule.isEnableLoadMore = false
    }
}