package com.yuanshenbin.basic.delegate;


import android.view.View;

import com.yuanshenbin.basic.state.OnEmptyListener;
import com.yuanshenbin.basic.state.OnRetryListener;
import com.yuanshenbin.basic.state.StateLayoutManager;

import java.io.Serializable;

import androidx.fragment.app.Fragment;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

public abstract class BaseFragmentDelegate extends IDelegate implements Serializable {


    public StateLayoutManager getStateLayoutManager(View root, OnRetryListener retryListener, OnEmptyListener emptyListener) {
        return null;
    }

    public void onConfig(Fragment activity) {

    }

    public void onDestroyView() {
    }


}
