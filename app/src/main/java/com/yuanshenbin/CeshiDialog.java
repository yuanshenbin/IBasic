package com.yuanshenbin;

import android.content.Context;

import com.yuanshenbin.basic.base.BaseDialog;
import com.yuanshenbin.basic.base.BasicViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * author : yuanshenbin
 * time   : 2022-01-29
 * desc   :
 */
public class CeshiDialog extends BaseDialog<BasicViewHolder,String> {
    @Override
    protected int initLayoutId() {
        return 0;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initEvents() {

    }

    public CeshiDialog(@NotNull Context context) {
        super(context);
    }

    public CeshiDialog(@NotNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
