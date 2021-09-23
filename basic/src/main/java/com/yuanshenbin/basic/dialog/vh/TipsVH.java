package com.yuanshenbin.basic.dialog.vh;


import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BasicViewHolder;

/**
 * author : yuanshenbin
 * time   : 2021-02-24
 * desc   :
 */
public class TipsVH extends BasicViewHolder {
    public TextView tv_title;
    public TextView tv_content;
    public LinearLayout ll_content;
    public TextView tv_cancel;
    public TextView tv_ok;
    public LinearLayout ll_bottom_styles;
    @Override
    protected int initLayoutId() {
        return R.layout.basic_dialog_tips;
    }

    public TipsVH(Window rootView) {
        super(rootView);
        this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        this.ll_content = (LinearLayout) rootView.findViewById(R.id.ll_content);
        this.tv_cancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        this.tv_ok = (TextView) rootView.findViewById(R.id.tv_ok);
        this.ll_bottom_styles = (LinearLayout) rootView.findViewById(R.id.ll_bottom_styles);
    }

}
