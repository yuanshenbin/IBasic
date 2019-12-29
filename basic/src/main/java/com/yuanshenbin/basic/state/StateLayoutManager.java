package com.yuanshenbin.basic.state;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author : yuanshenbin
 * time   : 2017/12/7
 * desc   :  页面状态管理
 */

public class StateLayoutManager {

    private Context context;
    private LinearLayout root;

    private ErrorState errorState;
    private TimeOutState timeOutState;
    private EmptyState emptyState;
    private LoadingState loadingState;
    private NetworkState networkState;

    public StateLayoutManager(Builder builder) {
        this.context = builder.context;
        this.errorState = builder.errorState;
        this.timeOutState = builder.timeOutState;
        this.emptyState = builder.emptyState;
        this.loadingState = builder.loadingState;
        this.networkState = builder.networkState;
        this.root = (LinearLayout) builder.root;

    }

    public LoadingState getLoadingState() {
        showLoading();
        return loadingState;
    }

    public void showLoading() {
        if (root != null) {
            loadingState.onViewCreated(context, root);
        }
    }


    public NetworkState getNetworkState() {
        showNetwork();
        return networkState;
    }

    public void showNetwork() {
        if (root != null) {
            networkState.onViewCreated(context, root);
        }
    }

    public TimeOutState getTimeOutState() {
        showTimeOut();
        return timeOutState;
    }

    public void showTimeOut() {
        if (root != null) {
            timeOutState.onViewCreated(context, root);
        }
    }


    public ErrorState getErrorState() {
        showError();
        return errorState;
    }

    public void showError() {
        if (root != null) {
            errorState.onViewCreated(context, root);
        }
    }

    public EmptyState getEmptyState() {
        showEmpty();
        return emptyState;
    }

    public void showDefault() {
        if (root != null) {
            root.removeAllViews();
            root.setVisibility(View.VISIBLE);
        }
    }

    public void showEmpty() {
        if (root != null) {
            emptyState.onViewCreated(context, root);
        }
    }

    public void showContent() {
        if (root != null) {
            if (root.getVisibility() == View.VISIBLE) {
                root.setVisibility(View.GONE);
            }
        }
    }
    public Context getContext() {
        return context;
    }

    public static final class Builder {
        private Context context;
        private View root;

        private ErrorState errorState;
        private TimeOutState timeOutState;
        private EmptyState emptyState;
        private LoadingState loadingState;
        private NetworkState networkState;

        public Builder(Context context, View root) {
            this.context = context;
            this.root = root;
        }


        public Builder errorStateView(ErrorState errorState) {
            this.errorState = errorState;
            return this;
        }

        public Builder timeOutStateView(TimeOutState timeOutState) {
            this.timeOutState = timeOutState;
            return this;
        }

        public Builder emptyStateView(EmptyState emptyState) {
            this.emptyState = emptyState;
            return this;
        }

        public Builder loadingStateView(LoadingState loadingState) {
            this.loadingState = loadingState;
            return this;
        }

        public Builder networkStateView(NetworkState networkState) {
            this.networkState = networkState;
            return this;
        }

        public StateLayoutManager build() {
            return new StateLayoutManager(this);
        }
    }

}
