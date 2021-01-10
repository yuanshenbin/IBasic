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

    public void setFill(Object result, int loading, int size, CharSequence emptyMsg) {
        swipe_to_load_layout.setRefreshing(false);
        if (mDelegate != null) {
            mDelegate.handleListData(mAdapter, mData, result, loading, size, emptyMsg);
        }
    }

    public void setFill(Object result, int loading, int size) {
        setFill(result, loading, size, null);
    }

    public void setFill(Object result, int loading) {
        setFill(result, loading, BasicConstants.PAGESIZE);
    }

    public void setFill(Object result, int loading, CharSequence empty) {
        setFill(result, loading, BasicConstants.PAGESIZE, empty);
    }

    protected void setListLoadFail() {
        swipe_to_load_layout.setRefreshing(false);
        if (!isPullAndPush) {
            mPage--;
            if (mAdapter.getLoadMoreModule() != null) {
                mAdapter.getLoadMoreModule().loadMoreFail();
            }
        }
    }
}
