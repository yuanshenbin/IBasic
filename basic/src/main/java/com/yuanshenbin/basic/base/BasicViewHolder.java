package com.yuanshenbin.basic.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

public abstract class BasicViewHolder {
    protected Context mContext;

    protected abstract int initLayoutId();

    public BasicViewHolder(ViewGroup rootView) {
        this.mContext = rootView.getContext();
    }

    public BasicViewHolder(View rootView) {
        this.mContext = rootView.getContext();
    }

    public BasicViewHolder(Window rootView) {
        this.mContext = rootView.getContext();
    }

    protected void IShowToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 不带参数跳转
     */
    protected void IStartActivity(Class<?> sla) {
        mContext.startActivity(new Intent(mContext, sla));
    }

    /**
     * 带参数跳转
     */
    protected void IStartActivity(Bundle bundle, Class<?> sla) {
        Intent intent = new Intent(mContext, sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    /**
     * 不带参数回传
     */
    protected void IStartActivityForResult(int requestCode, Class<?> sla) {

        if (mContext instanceof Activity) {
            ((Activity) mContext).startActivityForResult(new Intent(mContext, sla), requestCode);
        }

    }

    /**
     * 带参数回传
     */
    public void IStartActivityForResult(Bundle bundle, int requestCode, Class<?> sla) {

        Intent intent = new Intent(mContext, sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (mContext instanceof Activity) {
            if (mContext instanceof Activity) {
                ((Activity) mContext).startActivityForResult(intent, requestCode);
            }
        }
    }
}
