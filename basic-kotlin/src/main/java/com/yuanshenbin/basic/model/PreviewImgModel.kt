package com.yuanshenbin.basic.model

import android.content.res.Configuration
import java.io.Serializable

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   : 预览大图
 */
class PreviewImgModel : Serializable {
    var path: List<String>? = null
    var index = 0
    var orientation = Configuration.ORIENTATION_PORTRAIT

}