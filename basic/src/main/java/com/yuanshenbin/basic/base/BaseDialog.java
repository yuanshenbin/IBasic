package com.yuanshenbin.basic.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.call.Callback;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : yuanshenbin
 * time   : 2018/9/26
 * desc   :
 */

public abstract class BaseDialog<VH extends BasicViewHolder, Call> extends Dialog {
    protected Activity mActivity;
    protected VH mVH;


    protected VH initViewHolder() {
        return mVH;
    }

    protected boolean isFlag() {
        return true;
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        this.mActivity = (Activity) context;
        try {
            Window win = this.getWindow();
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            win.setGravity(getGravity());
            win.setBackgroundDrawableResource(R.color.core_basic_color_transparent);

            WindowManager.LayoutParams lp = win.getAttributes();
            win.getDecorView().setPadding(0, 0, 0, 0);
            lp.width = getWidth();
            lp.height = getHeight();
            lp.dimAmount = getDimAmount();
            lp.alpha = getAlpha();
            win.setAttributes(lp);
            setCancelable(getCancelable());
            setCanceledOnTouchOutside(getCanceledOnTouchOutside(true));
            win.setContentView(initLayoutId());
            if (getAnimations() != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    win.setWindowAnimations(getAnimations());
                }
            }
            initViews(win);
            if (isFlag()) {
                initConfig();
            }
            setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (callback != null) {
                        callback.close();
                    }
                }
            });

        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    protected void initConfig() {
        mVH = initViewHolder();
        if (mVH == null) {
            try {
                Type[] data = ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments();
                Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(Window.class);
                mVH = (VH) c.newInstance(getRootView());
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
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {

            }
        }
        initAdapter();
        initDatas();
        initEvents();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (callback != null) {
                    callback.close();
                }
            }
        });

    }

    protected void delayInitConfig() {

        try {
            Type[] data = ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments();
            Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(Window.class);
            mVH = (VH) c.newInstance(getWindow());

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
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {

        }
        initDatas();
        initEvents();
        initAdapter();


    }

    protected abstract int initLayoutId();

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

    protected void initViews(Window win) {

    }

    /**
     * 获得根view
     * @return
     */
    public Window getRootView() {
        return getWindow();
    }

    protected float getDimAmount() {
        return 0.6f;
    }

    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    protected float getAlpha() {
        return 1.0f;
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected boolean getCanceledOnTouchOutside(boolean cancel) {
        return cancel;
    }

    protected boolean getCancelable() {
        return true;
    }

    protected int getAnimations() {
        return 0;
    }

    @Override
    public void show() {
        if (mActivity != null && !mActivity.isFinishing() && !this.isShowing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (mActivity == null || !this.isShowing()) return;
        super.dismiss();

    }

    protected Callback<Call> callback;


    public BaseDialog setCallback(Callback<Call> callback) {
        this.callback = callback;
        return this;
    }
}
