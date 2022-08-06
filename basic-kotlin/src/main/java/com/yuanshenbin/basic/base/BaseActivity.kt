package com.yuanshenbin.basic.base

import android.R
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.yuanshenbin.basic.constant.BasicConstants
import com.yuanshenbin.basic.delegate.BaseActivityDelegate
import com.yuanshenbin.basic.state.OnEmptyListener
import com.yuanshenbin.basic.state.OnRetryListener
import com.yuanshenbin.basic.state.StateLayoutManager
import com.yuanshenbin.network.model.ResponseModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */
abstract class BaseActivity<VH : BasicViewHolder> : AppCompatActivity() {
    @JvmField
    protected var mDelegate: BaseActivityDelegate? = null

    @JvmField
    protected   var mContext: FragmentActivity? = null

    @JvmField
    protected var savedInstanceState: Bundle? = null

    @JvmField
    protected var mDisposable = CompositeDisposable()

    @JvmField
    protected var mStateLayoutManager: StateLayoutManager? = null

    @JvmField
    protected  var mVH: VH? = null

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
    open fun initIntentExtras(bundle: Bundle?) {}
    abstract fun initDelegate(): BaseActivityDelegate?

    /**
     * 布局id
     *
     * @return
     */
    abstract fun initLayoutId(): Int
    fun initViewHolder(): VH? {
        return mVH
    }

    private fun initConfig() {
        mVH = initViewHolder()


        if (mVH == null) {
            try {
                val data = (this.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments
                val c = Class.forName((data[0] as Class<*>).name).getConstructor(ViewGroup::class.java)
                mVH = c.newInstance(rootView) as VH
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
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
    abstract fun initDatas()

    /**
     * 初始化监听事件
     */
    abstract fun initEvents()

    /**
     * 初始化适配器
     */
    open fun initAdapter() {}

    /**
     * 缺醒图重试
     */
    fun onReload() {
        mDelegate?.onReload()
    }

    /**
     * 空页面点击
     *
     * @param text
     */
    fun onEmptyClick(vararg text: CharSequence?) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        mDelegate = initDelegate()
        mDelegate?.onStatusBar(this)
        super.onCreate(savedInstanceState)
        mDelegate?.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        //获取传递参数
        intent.extras?.let { initIntentExtras(it) }
        setContentView(initLayoutId())
    }

    override fun setContentView(layoutResID: Int) {
        if (layoutResID != 0) {
            super.setContentView(layoutResID)
        }
        initConfig()
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        initConfig()
    }

    /**
     * 配置共有的view
     */
    private fun doConfigBase() {

        mDelegate?.apply {
            onConfig(this@BaseActivity)
            mStateLayoutManager = mDelegate?.getStateLayoutManager(this@BaseActivity, object : OnRetryListener {
                override fun onRetry() {
                    this@BaseActivity.onReload()
                }
            }, object : OnEmptyListener {
                override fun onEmptyClick(vararg text: CharSequence?) {
                    this@BaseActivity.onEmptyClick()
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
        mDelegate?.onTitle(title)
    }

    /**
     * 获得根view
     *
     * @return
     */
    val rootView: ViewGroup
        get() = (findViewById<View>(R.id.content) as ViewGroup).getChildAt(0) as ViewGroup

    fun IShowToast(text: String?) {
        mDelegate?.onShowToast(this, text)
    }

    /**
     * 不带参数跳转
     */
    fun IStartActivity(sla: Class<*>?) {
        startActivity(Intent(mContext, sla))
    }

    /**
     * 带参数跳转
     */
    fun IStartActivity(bundle: Bundle?, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    /**
     * 不带参数回传
     */
    fun IStartActivityForResult(requestCode: Int, sla: Class<*>?) {
        startActivityForResult(Intent(mContext, sla), requestCode)
    }

    /**
     * 带参数回传
     */
    fun IStartActivityForResult(bundle: Bundle?, requestCode: Int, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        bundle?.let { intent.putExtras(it) }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 带参数回传回执
     */
    fun ISetResult(resultCode: Int, bundle: Bundle?) {
        val intent = Intent()
        bundle?.let { intent.putExtras(it) }
        setResult(resultCode, intent)
    }

    /**
     * 带参数回传回执
     */
    fun ISetResult(resultCode: Int, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        setResult(resultCode, intent)
    }

    /**
     * 带参数回传回执 带bundle
     */
    fun ISetResult(bundle: Bundle?, resultCode: Int, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        bundle?.let { intent.putExtras(it) }
        setResult(resultCode, intent)
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

    override fun onRestart() {
        super.onRestart()
        mDelegate?.onRestart()
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

    override fun onDestroy() {
        mDelegate?.onDestroy()
        mDisposable.clear()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        mDelegate?.finish()
    }

    override fun getResources(): Resources {
        return try {
            val res = super.getResources()
            val config = Configuration()
            config.setToDefaults()
            res.updateConfiguration(config, res.displayMetrics)
            res
        } catch (e: Exception) {
            super.getResources()
        }
    }
}