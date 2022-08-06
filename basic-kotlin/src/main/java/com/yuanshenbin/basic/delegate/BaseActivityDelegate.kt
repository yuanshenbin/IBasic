package com.yuanshenbin.basic.delegate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuanshenbin.basic.state.OnEmptyListener
import com.yuanshenbin.basic.state.OnRetryListener
import com.yuanshenbin.basic.state.StateLayoutManager
import java.io.Serializable

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */
abstract class BaseActivityDelegate : IDelegate(), Serializable {
    open fun onCreate(savedInstanceState: Bundle?) {}
    open fun onStatusBar(activity: AppCompatActivity?) {}
    open fun getStateLayoutManager(root: AppCompatActivity?, retryListener: OnRetryListener?, emptyListener: OnEmptyListener?): StateLayoutManager? {
        return null
    }

    open fun onConfig(activity: AppCompatActivity?) {}
    open fun onRestart() {}
    open fun onDestroy() {}
    open fun finish() {}
}