package com.yuanshenbin.basic.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yuanshenbin.basic.constant.BasicConstants;
import com.yuanshenbin.basic.delegate.BaseFragmentDelegate;
import com.yuanshenbin.basic.state.OnRetryListener;
import com.yuanshenbin.basic.state.StateLayoutManager;
import com.yuanshenbin.network.model.ResponseModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.disposables.CompositeDisposable;

/**
 * author : yuanshenbin
 * time   : 2017/9/22
 * desc   : 基类
 */
public abstract class BaseFragment<VH extends BasicViewHolder> extends SupportFragment {
    protected BaseFragmentDelegate mDelegate;


    protected CompositeDisposable mDisposable = new CompositeDisposable();
    protected StateLayoutManager mStateLayoutManager;

    protected VH mVH;
    /**
     * 分页当前页
     */
    protected int mPage = 1;

    /**
     * true 下拉
     * false 下拉
     */
    protected boolean isPullAndPush = true;


    /**
     * 初始化传来的数据
     */
    protected void initIntentExtras(Bundle bundle) {

    }

    protected abstract BaseFragmentDelegate initDelegate();

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int initLayoutId();

    protected VH initViewHolder() {
        return mVH;
    }


    private void initConfig() {
        mVH = initViewHolder();
        if (mVH == null) {
            try {
                Type[] data = ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments();
                Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(View.class);
                mVH = (VH) c.newInstance(mView);

            } catch (Fragment.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } finally {

            }
        }

        doConfigBase();
        initAdapter();
        initDatas();
        initEvents();

    }

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
    protected abstract void initDatas();

    /**
     * 初始化监听事件
     */
    protected abstract void initEvents();

    /**
     * 初始化适配器
     **/
    protected void initAdapter() {

    }

    /**
     * 缺醒图重试
     */
    public void onReload() {

        if (mDelegate != null)
            mDelegate.onReload();
    }


    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(initLayoutId(), container, false);

        mDelegate = initDelegate();
        Bundle bundle = getArguments();
        if (bundle != null) {
            initIntentExtras(bundle);
        }
        initConfig();
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    /**
     * 配置共有的view
     */
    private void doConfigBase() {
        if (mDelegate != null) {
            mDelegate.onConfig(this);
            mStateLayoutManager = mDelegate.getStateLayoutManager(mView, new OnRetryListener() {
                @Override
                public void onRetry() {
                    onReload();
                }
            });
        }

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(@NonNull String title) {
        if (mDelegate != null) {
            mDelegate.onTitle(title);
        }
    }

    /**
     * 获得根view
     *
     * @return
     */
    public View getRootView() {
        return mView;
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void IShowToast(String text) {

        if (mDelegate != null) {
            mDelegate.onShowToast(getActivity(), text);
        }
    }

    /**
     * 不带参数跳转
     */
    protected void IStartActivity(Class<?> sla) {
        startActivity(new Intent(getActivity(), sla));
    }

    /**
     * 带参数跳转
     */
    protected void IStartActivity(Bundle bundle, Class<?> sla) {
        Intent intent = new Intent(getActivity(), sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 不带参数回传
     */
    protected void IStartActivityForResult(int requestCode, Class<?> sla) {
        startActivityForResult(new Intent(getActivity(), sla), requestCode);
    }

    /**
     * 带参数回传
     */
    public void IStartActivityForResult(Bundle bundle, int requestCode, Class<?> sla) {
        Intent intent = new Intent(getActivity(), sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 列表加载状态
     *
     * @param loading
     * @return
     */
    protected boolean isLoading(int loading) {
        if (loading == BasicConstants.LOADING_STATE1) {
            return false;
        }
        if (loading == BasicConstants.LOADING_STATE2) {
            return false;
        }
        if (loading == BasicConstants.LOADING_STATE3) {
            return true;
        }
        return false;
    }


    /**
     * 下拉
     */
    protected void setPullAction() {
        mPage = 1;
        isPullAndPush = true;
    }

    /**
     * 上啦
     */
    protected void setPushAction() {
        mPage++;
        isPullAndPush = false;
    }


    /**
     * 判断列表数据是否显示empty的view
     *
     * @param pullAndPush
     * @param result
     * @return
     */
    protected boolean isListDataEmpty(boolean pullAndPush, Object result) {
        if (mDelegate != null) {
            return mDelegate.onListDataEmpty(pullAndPush, result);
        }
        return false;
    }

    /**
     * 页面加载状态
     *
     * @param model
     */
    public void setStateLayout(ResponseModel model) {
        if (mDelegate != null) {
            mDelegate.onStateLayout(model, mStateLayoutManager);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mDelegate != null) {
            mDelegate.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mDelegate != null) {
            mDelegate.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mDelegate != null) {
            mDelegate.onStop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mDelegate != null) {
            mDelegate.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        if (mDelegate != null) {
            mDelegate.onDestroyView();
        }
        mDisposable.clear();
        super.onDestroyView();
    }
}
