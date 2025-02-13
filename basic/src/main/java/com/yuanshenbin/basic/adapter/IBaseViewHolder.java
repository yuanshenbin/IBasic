package com.yuanshenbin.basic.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class IBaseViewHolder extends BaseViewHolder {
    public IBaseViewHolder(@NonNull View view) {
        super(view);

    }

    public int getIItemViewType() {

        return getItemViewType();
    }

    public int getILayoutPosition() {

        return getLayoutPosition();
    }

}
