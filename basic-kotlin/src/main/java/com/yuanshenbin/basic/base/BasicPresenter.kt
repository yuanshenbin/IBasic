package com.yuanshenbin.basic.base

import android.content.Context
import com.yuanshenbin.network.model.ResponseModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */
open class BasicPresenter<V> {
    @JvmField
    var mView: V? = null
    @JvmField
    var mContext: Context? = null
    @JvmField
    var mDisposable: CompositeDisposable? = null
    private var mRelativeLayout: BaseMvpRelativeLayout<*, *, *>? = null
    private var mLinearLayout: BaseMvpLinearLayout<*, *, *>? = null
    private var mBaseFragment: BaseFragment<*>? = null
    fun detach() {
        mView = null
        mContext = null
        if (mRelativeLayout != null) {
            mRelativeLayout!!.onDestroy()
        }
        if (mLinearLayout != null) {
            mLinearLayout!!.onDestroy()
        }
        mLinearLayout = null
        if (mDisposable != null) {
            mDisposable!!.clear()
        }
    }

    fun attach(view: V, context: BaseActivity<*>?) {
        mView = view
        mContext = context
    }

    fun attach(view: V, context: BaseDialog<*, *>) {
        mView = view
        mContext = context!!.context
    }

    fun attach(view: V, context: BaseFragment<*>) {
        mView = view
        mBaseFragment = context
        mContext = context!!.activity
    }

    fun attach(view: V, context: BaseMvpRelativeLayout<*, *, *>) {
        mView = view
        mRelativeLayout = context
        mContext = mRelativeLayout?.getContext()
    }

    fun attach(view: V, context: BaseMvpLinearLayout<*, *, *>) {
        mView = view
        mLinearLayout = context
        mContext = mLinearLayout?.getContext()
    }

    fun <T> append(observable: Observable<T>, observer: Observer<T>?) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer!!)
    }

    fun setResponseState(result: ResponseModel?) {
        if (mContext != null) {
            if (mBaseFragment != null) {
                mBaseFragment!!.setStateLayout(result)
            } else {
                (mContext as BaseActivity<*>).setStateLayout(result)
            }
        }
    }
}