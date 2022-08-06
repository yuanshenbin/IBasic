package com.yuanshenbin

import com.yuanshenbin.basic.base.BaseActivity
import com.yuanshenbin.basic.base.BasicViewHolder
import com.yuanshenbin.basic.delegate.BaseActivityDelegate
import com.yuanshenbin.ibasic.R

/**
 * author : yuanshenbin
 * time   : 2022-01-25
 * desc   :
 */
class TestAct : BaseActivity<BasicViewHolder>() {
    override fun initDelegate(): BaseActivityDelegate? {
        return null

    }

    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initDatas() {

    }

    override fun initEvents() {
//        tv_title.setOnClickListener {
//            TipsDialog.Builder(this,
//                    title = "!2",
//                    okContent = "12",
//                    content = "12",
//                    cancelContent = "12",
//                    callback = object : Callback<Any>() {
//                        override fun ok(t: Any) {
//
//                        }
//                    })
//                    .setLayoutRes(0, object : CustomDialogListener<Any> {
//                        override fun onCustomLayout(rootView: View?, dialog: Dialog?) {
//                            TODO("Not yet implemented")
//                        }
//
//                        override fun onOk(callback: Callback<Any>?) {
//                            TODO("Not yet implemented")
//                        }
//
//                        override fun onCancel(callback: Callback<Any>?) {
//                            TODO("Not yet implemented")
//                        }
//                    })
//                    .build()
//
//
//            TipsDialog.Builder(this)
//                    .setLayoutRes(0, object : CustomDialogListener {
//                        override fun onCustomLayout(rootView: View?, dialog: Dialog?) {
//                            TODO("Not yet implemented")
//                        }
//
//                        override fun onOk(callback: Callback<*>?) {
//                            TODO("Not yet implemented")
//                            if (c)
//                        }
//
//                        override fun onCancel(callback: Callback<String>?) {
//                            TODO("Not yet implemented")
//                        }
//                    })
//
//            PreviewManager.Builder(this
//            ).add("12").build()
//
//
//            DateUtils.let { it.getMillisToString(0, DateUtils.Type.HH_mm) }
//
//
//            CeshiDialog(this)
//                    .callback(object  : Callback<String>() {
//                        override fun ok(t: String) {
//                            TODO("Not yet implemented")
//                        }
//                    })
//            ImageLoader.instance.cacheSize
//        }

    }
}