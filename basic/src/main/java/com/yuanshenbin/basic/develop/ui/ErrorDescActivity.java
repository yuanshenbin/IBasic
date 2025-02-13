package com.yuanshenbin.basic.develop.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.adapter.CommonAdapter;
import com.yuanshenbin.basic.adapter.IBaseViewHolder;
import com.yuanshenbin.basic.base.BaseActivity;
import com.yuanshenbin.basic.db.DBManager;
import com.yuanshenbin.basic.delegate.BaseActivityDelegate;
import com.yuanshenbin.basic.model.ErrorModel;
import com.yuanshenbin.basic.util.IRecyclerDividerUtils;
import com.yuanshenbin.basic.util.IUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * author : yuanshenbin
 * time   : 2023/7/20
 * desc   :
 */
public class ErrorDescActivity extends BaseActivity<ErrorDescVH> {
    public static final String RESULT_ERROR_LIST = "error_list";
    private CommonAdapter<ErrorModel> mAdapter;
    private List<ErrorModel> mErrorModels = new ArrayList<>();

    private DBManager mDBManager;

    private boolean query_error_list;


    @Nullable
    @Override
    protected BaseActivityDelegate initDelegate() {
        return null;
    }

    @Override
    protected void initIntentExtras(@Nullable Bundle bundle) {
        super.initIntentExtras(bundle);
        query_error_list = bundle.getBoolean(RESULT_ERROR_LIST);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.basic_error_activity_error_desc;
    }

    @Override
    protected void initDatas() {
        mDBManager = new DBManager(this);
        try {
            mDBManager.open();
        } catch (SQLDataException throwables) {
            throwables.printStackTrace();
        }
        mVH.tv_title.setText("catch日志");
        mVH.tv_right_operate.setText("清空");
        mVH.layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mVH.tv_right_operate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBManager.deleteAllError();
                mErrorModels.clear();
                mAdapter.notifyDataSetChanged();
            }
        });


        if (!query_error_list) {
            final ErrorModel model = mVH.getAllErrorDetailsFromIntent(ErrorDescActivity.this, getIntent());
            mDBManager.insertError(model);
            showDialog(model.getDesc());
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                ErrorDescActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mErrorModels.clear();
                        mErrorModels.addAll(mDBManager.ListByAll());
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void initEvents() {

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                showDialog(mErrorModels.get(position).getDesc());
            }
        });

        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, final int position) {
                AlertDialog dialog = new AlertDialog.Builder(ErrorDescActivity.this)
                        .setTitle("提示")
                        .setMessage("是否操作："+mErrorModels.get(position).getCreateTime())
                        .setPositiveButton("分享", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it = new Intent("android.intent.action.SEND");
                                it.putExtra("android.intent.extra.SUBJECT", "发送catch日志");
                                it.putExtra("android.intent.extra.TEXT", mErrorModels.get(position).getDesc());
                                it.setType("text/plain");
                                startActivity(Intent.createChooser(it, "发送catch日志"));
                            }
                        })
                        .setNeutralButton("删除",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog1, int which) {
                                        mDBManager.deleteError(mErrorModels.get(position).getId());
                                        mErrorModels.remove(position);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                })
                        .show();
                dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF4081"));
                dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF4081"));

                return false;
            }
        });
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        mVH.swipe_target.setLayoutManager(new LinearLayoutManager(mContext));
        mVH.swipe_target.addItemDecoration(IRecyclerDividerUtils.getDivider1px(mContext));
        mAdapter = new CommonAdapter<ErrorModel>(R.layout.basic_error_activity_error_desc_item, mErrorModels) {
            @Override
            protected void convert(@NotNull IBaseViewHolder baseViewHolder, ErrorModel errorModel) {
                TextView tv_custom_crash_data = baseViewHolder.getView(R.id.tv_custom_crash_data);
                if (IUtils.isEmpty(errorModel.getCustomCrashData())) {
                    tv_custom_crash_data.setVisibility(View.GONE);
                } else {
                    tv_custom_crash_data.setVisibility(View.VISIBLE);
                    tv_custom_crash_data.setText(IUtils.noNull(errorModel.getCustomCrashData()));
                }
                baseViewHolder.setText(R.id.tv_error, IUtils.noNull(errorModel.getError()))
                        .setText(R.id.tv_time, IUtils.noNull(errorModel.getCreateTime()));

            }
        };
        mVH.swipe_target.setAdapter(mAdapter);
    }
    private void showDialog(final String desc){
        AlertDialog dialog = new AlertDialog.Builder(ErrorDescActivity.this)
                .setTitle("错误详情")
                .setMessage(desc)
                .setPositiveButton("关闭", null)
                .setNeutralButton("复制到剪贴板",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                copyErrorToClipboard(desc);
                            }
                        })
                .show();
        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF4081"));
        dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF4081"));
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }
    }

    private void copyErrorToClipboard( String errorInformation) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("错误信息", errorInformation);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(ErrorDescActivity.this, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(query_error_list){
            super.onBackPressed();
        }else {
            final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

            if (config == null) {
                //This should never happen - Just finish the activity to avoid a recursive crash.
                super.onBackPressed();
                return;
            }
            mDBManager.close();
            CustomActivityOnCrash.closeApplication(ErrorDescActivity.this, config);

        }

    }

    @Override
    protected void onDestroy() {
        mDBManager.close();
        super.onDestroy();

    }
}
