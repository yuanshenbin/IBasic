package com.yuanshenbin.basic.config

import android.graphics.Color
import android.view.Gravity

/**
 * author : yuanshenbin
 * time   : 2021-02-25
 * desc   : 提示框默认配置
 */
abstract class TipsAbstract {
    val title: String
        get() = "提示"

    val titleColor: Int
        get() = Color.parseColor("#000000")

    val okContentTitle: String
        get() = "确定"

    val okContentTitleColor: Int
        get() = Color.parseColor("#333333")

    val cancelContentTitle: String
        get() = "取消"

    val cancelContentColor: Int
        get() = Color.parseColor("#999999")

    val titleGravity: Int
        get() = Gravity.LEFT

    val contentGravity: Int
        get() = Gravity.LEFT
}