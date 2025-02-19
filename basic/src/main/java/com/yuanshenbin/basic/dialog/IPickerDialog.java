package com.yuanshenbin.basic.dialog;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BaseDialog;
import com.yuanshenbin.basic.call.Callback;
import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.dialog.vh.IPickerVH;
import com.yuanshenbin.basic.model.PickerModel;
import com.yuanshenbin.basic.util.IUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2021-02-14
 * desc   : 标准提示框
 */
public class IPickerDialog extends BaseDialog<IPickerVH, PickerModel> {
    private Builder mBuilder;

    private OptionsPickerView pvNoLinkOptions;


    public IPickerDialog(final Builder builder) {
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
        return R.layout.basic_dialog_picker;
    }

    @Override
    protected void initDatas() {
        callback=mBuilder.callback;
        mVH.tv_title.setText(mBuilder.title);
        mVH.tv_ok.setText(mBuilder.okContent);
        mVH.tv_cancel.setText(mBuilder.cancelContent);
        mVH.tv_cancel.setTextColor(mBuilder.cancelBtnColor);
        mVH.tv_ok.setTextColor(mBuilder.okBtnColor);


        final List<String> options = new ArrayList<>();

        final List<PickerModel> list = mBuilder.dataSource;

        if (!IUtils.isEmpty(list)) {
            for (PickerModel pickerModel : list) {
                options.add(pickerModel.getTitle());
            }
        }

        pvNoLinkOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                if (callback != null) {
                    callback.ok(list.get(options1));
                }
                dismiss();


            }
        }).setLayoutRes(R.layout.basic_view_picker_time, new CustomListener() {
            @Override
            public void customLayout(View v) {

            }
        })
                .setContentTextSize(16)//滚轮文字大小
                .setLineSpacingMultiplier(2.3f)
                .setDecorView(mVH.fl_frame)
                .setOutSideCancelable(false)
                .build();

        pvNoLinkOptions.setNPicker(options, null, null);
        pvNoLinkOptions.show(false);
    }

    @Override
    protected void initEvents() {
        mVH.tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvNoLinkOptions.returnData();


            }
        });
        mVH.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.callback != null) {
                    mBuilder.callback.cancel(null);
                }
                dismiss();
            }
        });
    }

    public static final class Builder {
        private Context context;
        private String title ;
        private Callback<PickerModel> callback;
        private List<PickerModel> dataSource;

        private String okContent = BasicOptions.getInstance().getTipsAbstract().getOkContentTitle();
        private String cancelContent = BasicOptions.getInstance().getTipsAbstract().getCancelContentTitle();

        private int okBtnColor = BasicOptions.getInstance().getTipsAbstract().getOkContentTitleColor();
        private int cancelBtnColor = BasicOptions.getInstance().getTipsAbstract().getCancelContentColor();

        public Builder(Context context) {
            this.context = context;
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

        public Builder dataSource(List<PickerModel> dataSource) {

            this.dataSource = dataSource;
            return this;
        }

        public Builder callback(Callback<PickerModel> callback) {
            this.callback = callback;
            return this;
        }


        public IPickerDialog build() {
            return new IPickerDialog(this);
        }
    }

    @Override
    protected int getWidth() {
        return getWidthPixels();
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected int getAnimations() {
        return R.style.basic_bottom_dialog;
    }
}
