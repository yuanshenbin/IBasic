package com.yuanshenbin.basic.dialog.vh;

import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BasicViewHolder;
import com.yuanshenbin.basic.widget.ITextView;

public class IPickerVH extends BasicViewHolder {
    public TextView tv_cancel;
    public TextView tv_ok;
    public ITextView tv_title;
    public FrameLayout fl_frame;

    public IPickerVH(Window rootView) {
        super(rootView);

        fl_frame = rootView.findViewById(R.id.fl_frame);
        tv_title=(ITextView) rootView.findViewById(R.id.tv_title);
        this.tv_cancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        this.tv_ok = (TextView) rootView.findViewById(R.id.tv_ok);
    }

    @Override
    protected int initLayoutId() {
        return 0;
    }
}
