package com.yuanshenbin.basic.base;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yuanshenbin.basic.adapter.CommonAdapter;
import com.yuanshenbin.basic.constant.BasicConstants;


import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */

public abstract class BaseListMvpFragment<VH extends BasicViewHolder, Bean, V, P extends BasePresenter<V>>
        extends BaseMvpFragment<VH, V, P> {
    protected SwipeToLoadLayout swipe_to_load_layout;
    protected List<Bean> mData = new ArrayList<>();
    protected CommonAdapter<Bean> mAdapter;

    public void handleListData(Object result, boolean pull, int page,int loading) {
        handleListData(result, pull, page, BasicConstants.PAGESIZE,loading);
    }

    public void handleListData(Object result, boolean pull, int page, int size,int loading) {
        swipe_to_load_layout.setRefreshing(false);
        if (mDelegate != null) {
            mDelegate.handleListData(mAdapter, mData, result, pull, page, size,loading);
        }
    }

    protected void setListLoadFail() {
        swipe_to_load_layout.setRefreshing(false);
        if (!isPullAndPush) {
            mPage--;
            mAdapter.loadMoreFail();
        }
    }
}