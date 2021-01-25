package com.yuanshenbin.basic.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;


import com.yuanshenbin.basic.constant.BasicConstants;
import com.yuanshenbin.basic.delegate.BaseActivityDelegate;
import com.yuanshenbin.basic.state.OnEmptyListener;
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
 * time   : 2018/10/24
 * desc   :
 */

public abstract class BaseActivity<VH extends BasicViewHolder> extends AppCompatActivity {

    protected BaseActivityDelegate mDelegate;
    protected FragmentActivity mContext;


    protected Bundle savedInstanceState;

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


    protected abstract BaseActivityDelegate initDelegate();

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
                Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(ViewGroup.class);
                mVH = (VH) c.newInstance(getRootView());


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
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
        if (mDelegate != null) {
            mDelegate.onReload();
        }
    }

    /**
     * 空页面点击
     *
     * @param text
     */
    public void onEmptyClick(CharSequence... text) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mContext = this;
        mDelegate = initDelegate();
        if (mDelegate != null) {
            mDelegate.onStatusBar(this);
        }
        super.onCreate(savedInstanceState);
        if (mDelegate != null)
            mDelegate.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        //获取传递参数
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            initIntentExtras(extras);
        }

        setContentView(initLayoutId());

    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID != 0) {
            super.setContentView(layoutResID);
        }

        initConfig();


    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initConfig();

    }

    /**
     * 配置共有的view
     */
    private void doConfigBase() {
        if (mDelegate != null) {
            mDelegate.onConfig(this);
            mStateLayoutManager = mDelegate.getStateLayoutManager(this, new OnRetryListener() {
                @Override
                public void onRetry() {
                    onReload();
                }
            }, new OnEmptyListener() {
                @Override
                public void onEmptyClick(CharSequence... text) {
                    BaseActivity.this.onEmptyClick();
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
        if (mDelegate != null)
            mDelegate.onTitle(title);
    }

    /**
     * 获得根view
     *
     * @return
     */
    public ViewGroup getRootView() {
        return (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    protected void IShowToast(String text) {
        if (mDelegate != null)
            mDelegate.onShowToast(this, text);
    }

    /**
     * 不带参数跳转
     */
    protected void IStartActivity(Class<?> sla) {
        startActivity(new Intent(mContext, sla));
    }

    /**
     * 带参数跳转
     */
    protected void IStartActivity(Bundle bundle, Class<?> sla) {
        Intent intent = new Intent(mContext, sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 不带参数回传
     */
    protected void IStartActivityForResult(int requestCode, Class<?> sla) {
        startActivityForResult(new Intent(mContext, sla), requestCode);
    }

    /**
     * 带参数回传
     */
    public void IStartActivityForResult(Bundle bundle, int requestCode, Class<?> sla) {
        Intent intent = new Intent(mContext, sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 带参数回传回执
     */
    protected void ISetResult(int resultCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(resultCode, intent);
    }

    /**
     * 带参数回传回执
     */
    protected void ISetResult(int resultCode, Class<?> sla) {
        Intent intent = new Intent(mContext, sla);
        setResult(resultCode, intent);
    }

    /**
     * 带参数回传回执 带bundle
     */
    protected void ISetResult(Bundle bundle, int resultCode, Class<?> sla) {
        Intent intent = new Intent(mContext, sla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(resultCode, intent);
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
        if (mDelegate != null)
            return mDelegate.onListDataEmpty(pullAndPush, result);
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
    protected void onResume() {
        super.onResume();
        if (mDelegate != null)
            mDelegate.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mDelegate != null)
            mDelegate.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mDelegate != null)
            mDelegate.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDelegate != null)
            mDelegate.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mDelegate != null)
            mDelegate.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mDelegate != null)
            mDelegate.onDestroy();
        mDisposable.clear();
        super.onDestroy();

    }

    @Override
    public void finish() {
        super.finish();
        if (mDelegate != null)
            mDelegate.finish();
    }

    @Override
    public Resources getResources() {
        try {
            Resources res = super.getResources();
            Configuration config = new Configuration();
            config.setToDefaults();
            res.updateConfiguration(config, res.getDisplayMetrics());
            return res;
        } catch (Exception e) {
            return super.getResources();
        }

    }
}
