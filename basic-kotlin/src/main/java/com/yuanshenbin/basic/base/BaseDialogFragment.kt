package com.yuanshenbin.basic.base

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.yuanshenbin.basic.constant.BasicConstants
import com.yuanshenbin.basic.delegate.BaseFragmentDelegate
import com.yuanshenbin.basic.state.OnEmptyListener
import com.yuanshenbin.basic.state.OnRetryListener
import com.yuanshenbin.basic.state.StateLayoutManager
import com.yuanshenbin.network.model.ResponseModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2017/9/22
 * desc   : 基类
 */
abstract class BaseDialogFragment<VH : BasicViewHolder?> : AppCompatDialogFragment() {
    @JvmField
    var mDelegate: BaseFragmentDelegate? = null

    @JvmField
    var mDisposable = CompositeDisposable()

    @JvmField
    var mStateLayoutManager: StateLayoutManager? = null

    @JvmField
    var mVH: VH? = null

    /**
     * 分页当前页
     */
    @JvmField
    var mPage = 1

    /**
     * true 下拉
     * false 下拉
     */
    @JvmField
    var isPullAndPush = true

    /**
     * 初始化传来的数据
     */
    fun initIntentExtras(bundle: Bundle?) {}
    abstract open fun initDelegate(): BaseFragmentDelegate?

    /**
     * 布局id
     *
     * @return
     */
    abstract open fun initLayoutId(): Int
    fun initViewHolder(): VH? {
        return mVH
    }

    private fun initConfig() {
        mVH = initViewHolder()
        if (mVH == null) {
            try {
                val data = (this.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments
                val c = Class.forName((data[0] as Class<*>).name).getConstructor(View::class.java)
                mVH = c.newInstance(rootView) as VH
            } catch (e: Fragment.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            } finally {
            }
        }
        doConfigBase()
        initAdapter()
        initDatas()
        initEvents()
    }

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
    abstract open fun initDatas()

    /**
     * 初始化监听事件
     */
    abstract open fun initEvents()

    /**
     * 初始化适配器
     */
    open fun initAdapter() {}

    /**
     * 缺醒图重试
     */
    fun onReload() {
        if (mDelegate != null) mDelegate!!.onReload()
    }

    /**
     * 空页面点击
     * @param text
     */
    fun onEmptyClick(vararg text: CharSequence?) {}

    /**
     * 获得根view
     *
     * @return
     */
    var rootView: View? = null
        set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(initLayoutId(), container, false)
        mDelegate = initDelegate()
        val bundle = arguments
        bundle?.let { initIntentExtras(it) }
        initConfig()
        return rootView
    }

    /**
     * 配置共有的view
     */
    private fun doConfigBase() {
        if (mDelegate != null) {
            mDelegate!!.onConfig(this)
            mStateLayoutManager = mDelegate!!.getStateLayoutManager(rootView,
                    object : OnRetryListener {
                        override fun onRetry() {
                            this@BaseDialogFragment.onReload()
                        }
                    },
                    object : OnEmptyListener {
                        override fun onEmptyClick(vararg text: CharSequence?) {
                            this@BaseDialogFragment.onEmptyClick(*text)
                        }
                    })
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    fun setTitle(title: String) {
        if (mDelegate != null) {
            mDelegate!!.onTitle(title)
        }
    }

    /**
     * 短暂显示Toast提示(来自String)
     */
    fun IShowToast(text: String?) {
        if (mDelegate != null) {
            mDelegate!!.onShowToast(activity, text)
        }
    }

    /**
     * 不带参数跳转
     */
    fun IStartActivity(sla: Class<*>?) {
        startActivity(Intent(activity, sla))
    }

    /**
     * 带参数跳转
     */
    fun IStartActivity(bundle: Bundle?, sla: Class<*>?) {
        val intent = Intent(activity, sla)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 不带参数回传
     */
    fun IStartActivityForResult(requestCode: Int, sla: Class<*>?) {
        startActivityForResult(Intent(activity, sla), requestCode)
    }

    /**
     * 带参数回传
     */
    fun IStartActivityForResult(bundle: Bundle?, requestCode: Int, sla: Class<*>?) {
        val intent = Intent(activity, sla)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 列表加载状态
     *
     * @param loading
     * @return
     */
    fun isLoading(loading: Int): Boolean {
        if (loading == BasicConstants.LOADING_STATE1) {
            return false
        }
        if (loading == BasicConstants.LOADING_STATE2) {
            return false
        }
        return loading == BasicConstants.LOADING_STATE3
    }

    /**
     * 下拉
     */
    fun setPullAction() {
        mPage = 1
        isPullAndPush = true
    }

    /**
     * 上啦
     */
    fun setPushAction() {
        mPage++
        isPullAndPush = false
    }


    /**
     * 判断列表数据是否显示empty的view
     *
     * @param pullAndPush
     * @param result
     * @return
     */
    fun isListDataEmpty(pullAndPush: Boolean, result: Any?): Boolean {
        return mDelegate!!.onListDataEmpty(pullAndPush, result)
    }

    /**
     * 页面加载状态
     *
     * @param model
     */
    fun setStateLayout(model: ResponseModel?) {

        mDelegate?.onStateLayout(model, mStateLayoutManager)
    }

    override fun onResume() {
        super.onResume()
        mDelegate?.onResume()
    }


    override fun onStart() {
        super.onStart()
        mDelegate?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mDelegate?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mDelegate?.onPause()
    }

    override fun onDestroyView() {
        mDelegate?.onDestroyView()
        mDisposable.clear()
        super.onDestroyView()
    }
}