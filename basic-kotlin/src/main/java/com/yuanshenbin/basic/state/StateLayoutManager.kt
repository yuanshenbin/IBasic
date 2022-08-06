package com.yuanshenbin.basic.state

import android.content.Context
import android.view.View
import android.widget.LinearLayout

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   :  页面状态管理
 */
class StateLayoutManager(builder: Builder) {
    val context: Context
    private val root: LinearLayout?
    private val errorState: ErrorState?
    private val timeOutState: TimeOutState?
    private val emptyState: EmptyState?
    private val loadingState: LoadingState?
    private val networkState: NetworkState?
    fun getLoadingState(): LoadingState? {
        showLoading()
        return loadingState
    }

    fun showLoading() {
        if (root != null) {
            loadingState!!.onViewCreated(context, root)
        }
    }

    fun getNetworkState(): NetworkState? {
        showNetwork()
        return networkState
    }

    fun showNetwork() {
        if (root != null) {
            networkState!!.onViewCreated(context, root)
        }
    }

    fun getTimeOutState(): TimeOutState? {
        showTimeOut()
        return timeOutState
    }

    fun showTimeOut() {
        if (root != null) {
            timeOutState!!.onViewCreated(context, root)
        }
    }

    fun getErrorState(): ErrorState? {
        showError()
        return errorState
    }

    fun showError() {
        if (root != null) {
            errorState!!.onViewCreated(context, root)
        }
    }

    fun getEmptyState(): EmptyState? {
        showEmpty()
        return emptyState
    }

    fun showDefault() {
        if (root != null) {
            root.removeAllViews()
            root.visibility = View.VISIBLE
        }
    }

    fun showEmpty() {
        if (root != null) {
            emptyState!!.onViewCreated(context, root)
        }
    }

    fun showContent() {
        if (root != null) {
            if (root.visibility == View.VISIBLE) {
                root.visibility = View.GONE
            }
        }
    }

    class Builder(internal var context: Context, internal var root: View,
                  internal var errorState: ErrorState? = null,
                  internal var timeOutState: TimeOutState? = null,
                  internal var emptyState: EmptyState? = null,
                  internal var loadingState: LoadingState? = null,
                  internal var networkState: NetworkState? = null
    ) {

        constructor(context: Context, root: View) :
                this(context, root, null, null, null, null, null) {
            this.context = context
            this.root = root
        }

        fun errorStateView(errorState: ErrorState?): Builder {
            this.errorState = errorState
            return this
        }

        fun timeOutStateView(timeOutState: TimeOutState?): Builder {
            this.timeOutState = timeOutState
            return this
        }

        fun emptyStateView(emptyState: EmptyState?): Builder {
            this.emptyState = emptyState
            return this
        }

        fun loadingStateView(loadingState: LoadingState?): Builder {
            this.loadingState = loadingState
            return this
        }

        fun networkStateView(networkState: NetworkState?): Builder {
            this.networkState = networkState
            return this
        }

        fun build(): StateLayoutManager {
            return StateLayoutManager(this)
        }

    }

    init {
        context = builder.context
        errorState = builder.errorState
        timeOutState = builder.timeOutState
        emptyState = builder.emptyState
        loadingState = builder.loadingState
        networkState = builder.networkState
        this.root = builder.root as LinearLayout
    }
}