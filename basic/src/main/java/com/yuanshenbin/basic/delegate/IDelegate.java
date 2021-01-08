package com.yuanshenbin.basic.delegate;

import android.content.Context;

import com.yuanshenbin.basic.adapter.CommonAdapter;
import com.yuanshenbin.basic.state.StateLayoutManager;
import com.yuanshenbin.basic.util.NetworkUtils;
import com.yuanshenbin.network.model.ResponseModel;

import java.io.Serializable;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

abstract class IDelegate implements Serializable {

    public void onShowToast(Context context, String str) {

    }


    public void onStateLayout(ResponseModel model, StateLayoutManager layoutManager) {
        if (layoutManager != null && model != null) {
            switch (model.getState()) {
                case 开始:
                    layoutManager.showLoading();
                    break;
                case 成功:
                    layoutManager.showContent();
                    break;
                case 结束:
                    break;
                case 失败:
                    if (NetworkUtils.isNetworkConnected(layoutManager.getContext())) {
                        layoutManager.getNetworkState().showNetwork("网络似乎开小差了", "重试");
                    } else {
                        layoutManager.showNetwork();
                    }
                    break;
                default:
            }
        }
    }

    public boolean onListDataEmpty(boolean pullAndPush, Object result) {


        return false;
    }

    public void handleListData(CommonAdapter adapter, Object result, int loading, int size, CharSequence emptyMsg){}


    public void onTitle(String title) {

    }

    public void onStart() {

    }

    public void onStop() {
    }

    public void onResume() {
    }

    public void onPause() {

    }

    public void onReload() {

    }

}
