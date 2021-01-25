package com.yuanshenbin.basic.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yuanshenbin.basic.util.ToastUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */
public abstract class BaseReletiveLayout<VH extends BasicViewHolder> extends RelativeLayout {

    protected Context mContext;
    protected VH mVH;

    public boolean isFlag() {
        return true;
    }

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int initLayoutId();


    protected VH initViewHolder() {
        return mVH;
    }

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
    protected void initViews() {
        mContext = getContext();
        LayoutInflater.from(getContext()).inflate(initLayoutId(), this, true);

        mVH = initViewHolder();
        if (mVH == null) {
            try {
                Type[] data = ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments();
                Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(ViewGroup.class);
                mVH = (VH) c.newInstance(this);

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
    protected void initAttributeSet(AttributeSet attrs) {

    }

    public BaseReletiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(attrs);
        if (isFlag()) {
            initViews();
        }
    }

    protected void IShowToast(String str) {
        ToastUtils.shortToast(mContext,str);
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
}
