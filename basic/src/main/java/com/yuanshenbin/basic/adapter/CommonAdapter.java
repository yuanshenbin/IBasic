package com.yuanshenbin.basic.adapter;



import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2017/9/27
 * desc   :
 */

public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public CommonAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    /**
     * @param layoutResId
     * @param data
     * @param recyclerView 不传没有分页效果
     */
    public CommonAdapter(int layoutResId, List<T> data, RecyclerView recyclerView) {
        super(layoutResId, data);
        this.setOnLoadMoreListener(new RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (l != null) {
                    l.onLoadMore();
                }

            }
        }, recyclerView);
    }

    public OnMoreLoadListener l;

    public interface OnMoreLoadListener {
        void onLoadMore();
    }

    public void setOnMoreLoadListener(OnMoreLoadListener l) {
        this.l = l;
    }

}
