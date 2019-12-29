package com.yuanshenbin.basic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : yuanshenbin
 * time   : 2018/3/3
 * desc   : 懒加载，以及可见
 */

public abstract class SupportFragment extends Fragment {
    private static final String FIRST = "first";
    private static final String RESUME = "resume";
    private static final String HIDDEN = "hidden";
    private static final String LAZYLOAD = "lazyload";
    private boolean isFirst;
    private boolean isHidden;
    private boolean isResume;
    private boolean isCreate;
    private boolean isLazyload;
    private int mMode = MODE.HiddenChanged.mode;

    public enum MODE {
        HiddenChanged(0),
        UserVisibleHint(1);
        public int mode;

        MODE(int mode) {
            this.mode = mode;
        }

    }

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int initLayoutId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCreate = true;
        return inflater.inflate(initLayoutId(), container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {

            isFirst = savedInstanceState.getBoolean(FIRST, false);
            isResume = savedInstanceState.getBoolean(RESUME, false);
            isHidden = savedInstanceState.getBoolean(HIDDEN, false);
            isLazyload = savedInstanceState.getBoolean(LAZYLOAD, false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMode == MODE.UserVisibleHint.mode) {
            if (!isResume && getUserVisibleHint()) {
                isResume = true;
                if (!isFirst) {
                    if (isLazyload) {
                        return;
                    }
                    isFirst = true;
                    isLazyload = true;

                    onSupportLazyLoad();
                    onSupportVisible();

                } else {
                    if (!isResume) {
                        return;
                    }
                    onSupportVisible();
                }
                return;
            }
            if (isResume && isLazyload && getUserVisibleHint()) {
                onSupportVisible();
            }
        } else {
            if (!isResume) {
                if (!isFirst && !isHidden) {
                    if (isLazyload) {
                        return;
                    }
                    isFirst = true;
                    isLazyload = true;
                    onSupportLazyLoad();
                    onSupportVisible();

                } else {
                    if (!isResume) {
                        isResume = true;
                        return;
                    }
                    onSupportVisible();
                }
                isResume = true;
                return;
            }
            if (isResume && !isHidden && isFirst && isLazyload) {
                onSupportVisible();
            }
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mMode = MODE.HiddenChanged.mode;
        this.isHidden = hidden;
        if (isResume) {
            if (!isFirst && !hidden) {
                if (isLazyload) {
                    onSupportVisible();
                    return;
                }
                isFirst = true;
                isLazyload = true;
                onSupportLazyLoad();
                onSupportVisible();

                return;
            }
            if (!isHidden) {
                onSupportVisible();
            }
        } else {
            if (!isFirst && !hidden) {
                isFirst = true;
                isLazyload = true;
                onSupportLazyLoad();
                onSupportVisible();

                return;
            }
        }

    }


    /**
     * 懒加载
     */
    protected void onSupportLazyLoad() {
    }

    /**
     * 每次可见的时候都会触发
     */
    protected void onSupportVisible() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FIRST, isFirst);
        outState.putBoolean(RESUME, isResume);
        outState.putBoolean(HIDDEN, isHidden);
        outState.putBoolean(LAZYLOAD, isLazyload);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mMode = MODE.UserVisibleHint.mode;
        if (isVisibleToUser) {
            if (!isFirst && !isResume) {
                if (!isCreate) {
                    return;
                }
                isFirst = true;
                isLazyload = true;
                onSupportLazyLoad();
                onSupportVisible();

                return;
            }
            onSupportVisible();
        }
    }
}
