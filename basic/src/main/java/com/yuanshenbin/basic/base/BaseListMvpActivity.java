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

public abstract class BaseListMvpActivity<VH extends BasicViewHolder, Bean, V, P extends BasePresenter<V>>
        extends BaseMvpActivity<VH, V, P> {
    protected SwipeToLoadLayout swipe_to_load_layout;
    protected List<Bean> mData = new ArrayList<>();
    protected CommonAdapter<Bean> mAdapter;

    public void handleListData(Object result, boolean pull, int page, int loading) {
        this.handleListData(result, pull, page, 20, loading);
    }

    public void handleListData(Object result, boolean pull, int page, int size, int loading) {
        this.swipe_to_load_layout.setRefreshing(false);
        if (this.mDelegate != null) {
            this.mDelegate.handleListData(this.mAdapter, this.mData, result, pull, page, size, loading);
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
