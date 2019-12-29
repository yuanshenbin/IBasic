package com.yuanshenbin.basic.base;

import android.content.Context;
import com.yuanshenbin.network.model.ResponseModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */

public class BasicPresenter<V> {
    public V mView;
    public Context mContext;
    public CompositeDisposable mDisposable;
    private BaseMvpReletiveLayout mRelativeLayout;
    private BaseMvpLinearLayout mLinearLayout;
    private BaseFragment mBaseFragment;

    public void detach() {
        mView = null;
        mContext = null;
        if (mRelativeLayout != null) {
            mRelativeLayout.onDestroy();
            mRelativeLayout = null;
        }
        if (mLinearLayout != null) {
            mLinearLayout.onDestroy();
            mLinearLayout = null;
        }
        mLinearLayout = null;
        if (mDisposable != null) {
            mDisposable.clear();
        }

    }

    public void attach(V view, BaseActivity context) {
        mView = view;
        mContext = context;
    }
    public void attach(V view, BaseDialog context) {
        mView = view;
        mContext = context.getContext();
    }

    public void attach(V view, BaseFragment context) {
        mView = view;
        mBaseFragment = context;
        mContext = context.getActivity();
    }

    public void attach(V view, BaseMvpReletiveLayout context) {
        mView = view;
        mRelativeLayout = context;
        mContext = context.getContext();
    }

    public void attach(V view, BaseMvpLinearLayout context) {
        mView = view;
        mLinearLayout = context;
        mContext = context.getContext();

    }

    public <T> void append(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void setResponseState(ResponseModel result) {
        if (mContext != null) {
            if (mBaseFragment != null) {
                mBaseFragment.setStateLayout(result);
            } else {
                ((BaseActivity) mContext).setStateLayout(result);
            }
        }
    }
}
