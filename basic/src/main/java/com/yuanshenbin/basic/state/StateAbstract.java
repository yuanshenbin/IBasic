package com.yuanshenbin.basic.state;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   :
 */

public abstract class StateAbstract {
    protected Context mContext;
    protected LinearLayout root;

    @LayoutRes
    public abstract int onCreateView();


    public void onViewCreated(Context context, LinearLayout root) {
        this.mContext = context;
        this.root = root;
        if (root.getVisibility() == View.GONE) {
            root.setVisibility(View.VISIBLE);
        }
        removeViews(root);
        ViewStub viewStub = new ViewStub(context);
        viewStub.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewStub.setLayoutResource(onCreateView());
        root.addView(viewStub);
        viewStub.inflate();
    }

    public void removeViews(LinearLayout root) {
        root.removeAllViews();
    }

} 
