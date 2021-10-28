package com.yuanshenbin.basic.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2017/9/27
 * desc   :
 */

public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements LoadMoreModule {
    /**
     * @param layoutResId
     * @param data
     */
    public CommonAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
        getLoadMoreModule().setEnableLoadMore(false);
        getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (l != null) {
                    l.onLoadMore();
                }
            }
        });

    }


    public OnMoreLoadListener l;

    public interface OnMoreLoadListener {
        void onLoadMore();
    }

    public void setOnMoreLoadListener(OnMoreLoadListener l) {
        getLoadMoreModule().setEnableLoadMore(true);
        this.l = l;
    }

}
