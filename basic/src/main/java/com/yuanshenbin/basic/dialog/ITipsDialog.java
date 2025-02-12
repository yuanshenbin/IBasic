package com.yuanshenbin.basic.dialog;

import android.content.Context;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BaseDialog;
import com.yuanshenbin.basic.call.Callback;
import com.yuanshenbin.basic.call.CustomDialogListener;
import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.dialog.vh.ITipsVH;
import com.yuanshenbin.basic.util.IUtils;

/**
 * author : yuanshenbin
 * time   : 2021-02-14
 * desc   : 标准提示框
 */
public class ITipsDialog extends BaseDialog<ITipsVH, Object> {
    private Builder mBuilder;

    public ITipsDialog(final Builder builder) {
        super(builder.context);
        mBuilder = builder;
    }


    @Override
    protected void initConfig() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                delayInitConfig();
                show();
            }
        });
    }

    @Override
    protected int initLayoutId() {
        return R.layout.basic_dialog_tips;
    }

    @Override
    protected void initDatas() {

        if (mBuilder.single) {
            mVH.tv_cancel.setVisibility(View.GONE);
        }
        if (!IUtils.isEmpty(mBuilder.title)) {
            mVH.tv_title.setText(mBuilder.title);
        } else {
            mVH.tv_title.setVisibility(View.GONE);
        }

        if (mBuilder.listener != null && mBuilder.resId != 0) {
            View view = LayoutInflater.from(mActivity).inflate(mBuilder.resId, null);
            mVH.ll_content.removeAllViews();
            mVH.ll_content.addView(view);
            mBuilder.listener.onCustomLayout(view, this);
        } else {
            if (!IUtils.isEmpty(mBuilder.content)) {
                mVH.tv_content.setText(mBuilder.content);
                if (mBuilder.content.length() > 150) {
                    mVH.tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
                }
            }
        }
        mVH.tv_content.setGravity(mBuilder.contentGravity);
        mVH.tv_title.setGravity(mBuilder.titleGravity);
        mVH.tv_ok.setText(mBuilder.okContent);
        mVH.tv_cancel.setText(mBuilder.cancelContent);
        mVH.tv_cancel.setTextColor(mBuilder.cancelBtnColor);
        mVH.tv_ok.setTextColor(mBuilder.okBtnColor);

        setCanceledOnTouchOutside(mBuilder.cancelable);
        setCancelable(mBuilder.cancelable);
    }

    @Override
    protected void initEvents() {
        mVH.tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.listener != null && mBuilder.resId != 0) {
                    mBuilder.listener.onOk(mBuilder.callback);
                } else {
                    if (mBuilder.callback != null) {
                        mBuilder.callback.ok("");
                    }
                    dismiss();
                }


            }
        });
        mVH.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.listener != null && mBuilder.resId != 0) {
                    mBuilder.listener.onCancel(mBuilder.callback);
                } else {
                    if (mBuilder.callback != null) {
                        mBuilder.callback.cancel("");
                    }
                    dismiss();
                }
            }
        });
    }

    public static final class Builder {
        private Context context;
        private String title = BasicOptions.getInstance().getTipsAbstract().getTitle();//标题
        private CharSequence content;//内容
        private boolean single = false;//是单选
        private Callback callback;
        private boolean cancelable = true;//控制dialog是否允许返回  true是可以
        private String okContent = BasicOptions.getInstance().getTipsAbstract().getOkContentTitle();
        private String cancelContent = BasicOptions.getInstance().getTipsAbstract().getCancelContentTitle();
        private CustomDialogListener listener;
        private int resId;
        private int okBtnColor = BasicOptions.getInstance().getTipsAbstract().getOkContentTitleColor();
        private int cancelBtnColor = BasicOptions.getInstance().getTipsAbstract().getCancelContentColor();
        private Integer titleGravity = BasicOptions.getInstance().getTipsAbstract().getTitleGravity();
        private Integer contentGravity = BasicOptions.getInstance().getTipsAbstract().getContentGravity();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder titleGravity(int titleGravity) {
            this.titleGravity = titleGravity;
            return this;
        }

        public Builder contentGravity(int contentGravity) {
            this.contentGravity = contentGravity;
            return this;
        }

        public Builder okBtnColor(int okBtnColor) {
            this.okBtnColor = okBtnColor;
            return this;
        }

        public Builder cancelBtnColor(int cancelBtnColor) {
            this.cancelBtnColor = cancelBtnColor;
            return this;
        }

        public Builder okContent(String okContent) {
            this.okContent = okContent;
            return this;
        }

        public Builder cancelContent(String cancelContent) {
            this.cancelContent = cancelContent;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(CharSequence content) {
            this.content = content;
            return this;
        }

        public Builder isSingle(boolean single) {
            this.single = single;
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Builder isCancelable(boolean flag) {
            this.cancelable = flag;
            return this;
        }


        public Builder setLayoutRes(int resId, CustomDialogListener listener) {
            this.listener = listener;
            this.resId = resId;
            return this;

        }

        public ITipsDialog build() {
            return new ITipsDialog(this);
        }
    }

    @Override
    protected int getWidth() {
        return getWidthDefault();
    }

    @Override
    protected int getAnimations() {
        return R.style.basic_tips_dialog;
    }
}
