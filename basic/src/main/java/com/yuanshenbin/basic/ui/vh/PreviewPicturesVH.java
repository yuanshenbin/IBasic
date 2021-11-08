package com.yuanshenbin.basic.ui.vh;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BasicViewHolder;
import com.yuanshenbin.basic.widget.HackyViewPager;
import com.yuanshenbin.basic.widget.ITextView;

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   :
 */
public class PreviewPicturesVH extends BasicViewHolder {
    public HackyViewPager view_pager;
    public LinearLayout ll_back;
    public ITextView tv_title;

    @Override
    protected int initLayoutId() {
        return R.layout.base_activity_preview_pictures;
    }

    public PreviewPicturesVH(ViewGroup rootView) {
        super(rootView);
        this.view_pager = (HackyViewPager) rootView.findViewById(R.id.view_pager);
        this.ll_back = (LinearLayout) rootView.findViewById(R.id.ll_back);
        this.tv_title = (ITextView) rootView.findViewById(R.id.tv_title);
    }

}
