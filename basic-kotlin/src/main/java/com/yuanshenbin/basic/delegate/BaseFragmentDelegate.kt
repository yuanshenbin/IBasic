package com.yuanshenbin.basic.delegate

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yuanshenbin.basic.state.OnEmptyListener
import com.yuanshenbin.basic.state.OnRetryListener
import com.yuanshenbin.basic.state.StateLayoutManager
import java.io.Serializable

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */
abstract class BaseFragmentDelegate : IDelegate(), Serializable {
    fun getStateLayoutManager(root: View?, retryListener: OnRetryListener?, emptyListener: OnEmptyListener?): StateLayoutManager? {
        return null
    }

    fun onConfig(activity: Fragment?) {}
    fun onDestroyView() {}
}