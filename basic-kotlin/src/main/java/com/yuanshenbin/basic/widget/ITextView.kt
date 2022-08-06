package com.yuanshenbin.basic.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import com.yuanshenbin.basic.R

/**
 * author : yuanshenbin
 * time   : 2018/12/11
 * desc   :
 */
class ITextView : AppCompatTextView {
    private var i_pressed_color= 0//按下的颜色 = 0
    private var i_normal_color= 0 //正常状态颜色 = 0
    private var i_enabled_color= 0//不可用的颜色 = 0
    private var i_stroke_color= 0//边线的颜色 = 0
    private var i_solid_color= 0//填充颜色 = 0

    @DimenRes
    private var i_radius_size= 0 //弧度大小 = 0

    @DimenRes
    private var i_stroke_width= 0 //边线的宽 = 0
    private var i_text_color= 0 //字体颜色 = 0

    /**
     * 未选中
     */
    private var i_pressed_color_unselected= 0//未选中按下的颜色 = 0
    private var i_normal_color_unselected= 0 //未选中正常状态颜色 = 0
    private var i_enabled_color_unselected= 0 //未选中不可用的颜色 = 0
    private var i_stroke_color_unselected= 0//未选中边线的颜色 = 0
    private var i_solid_color_unselected= 0 //未选中填充颜色 = 0

    @DimenRes
    private var i_stroke_width_unselected= 0 //未选中边线的宽 = 0
    private var i_text_color_unselected= 0 //字体颜色 = 0
    private var i_text_style= 0 //字体粗 = 0

    /**
     * 获取选中状态
     *
     * @return
     */
    /**
     * 是否选中状态
     */
    private  var isSelectedState = false

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        if (attrs != null) {
            val ta = getContext().obtainStyledAttributes(attrs, R.styleable.ITextView)
            i_text_style = ta.getInt(R.styleable.ITextView_i_text_style, 0)
            isSelectedState = ta.getBoolean(R.styleable.ITextView_i_selected, true)
            i_text_color = ta.getColor(R.styleable.ITextView_i_text_color, 0)
            i_pressed_color = ta.getColor(R.styleable.ITextView_i_pressed_color, 0)
            i_normal_color = ta.getColor(R.styleable.ITextView_i_normal_color, 0)
            i_enabled_color = ta.getColor(R.styleable.ITextView_i_enabled_color, 0)
            i_stroke_color = ta.getColor(R.styleable.ITextView_i_stroke_color, 0)
            i_solid_color = ta.getColor(R.styleable.ITextView_i_solid_color, Color.parseColor("#00000000"))
            i_radius_size = ta.getDimension(R.styleable.ITextView_i_radius_size, 0f).toInt()
            i_stroke_width = ta.getDimension(R.styleable.ITextView_i_stroke_width, 0f).toInt()
            i_normal_color_unselected = ta.getColor(R.styleable.ITextView_i_normal_color_unselected, 0)
            i_enabled_color_unselected = ta.getColor(R.styleable.ITextView_i_enabled_color_unselected, 0)
            i_stroke_color_unselected = ta.getColor(R.styleable.ITextView_i_stroke_color_unselected, 0)
            i_solid_color_unselected = ta.getColor(R.styleable.ITextView_i_solid_color_unselected, 0)
            i_pressed_color_unselected = ta.getColor(R.styleable.ITextView_i_pressed_color_unselected, 0)
            i_stroke_width_unselected = ta.getDimension(R.styleable.ITextView_i_stroke_width_unselected, 0f).toInt()
            i_text_color_unselected = ta.getColor(R.styleable.ITextView_i_text_color_unselected, 0)
            ta.recycle()
        }
        selectedState(isSelectedState)
    }

    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    private fun getSolidRectDrawable(cornerRadius: Int, solidColor: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        // 设置矩形的圆角半径
        gradientDrawable.cornerRadius = cornerRadius.toFloat()
        if(solidColor!=0){
            // 设置绘画图片色值
            gradientDrawable.setColor(solidColor)
        }
        gradientDrawable.setStroke(i_stroke_width, i_stroke_color)
        // 绘画的是矩形
        gradientDrawable.gradientType = GradientDrawable.RADIAL_GRADIENT
        return gradientDrawable
    }

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    private fun getStateListDrawable(pressedDrawable: Drawable?, normalDrawable: Drawable?): StateListDrawable? {
        if (pressedDrawable == null && normalDrawable == null) {
            return null
        }
        val stateListDrawable = StateListDrawable()
        if (pressedDrawable != null) {
            stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed), pressedDrawable)
        }
        if (normalDrawable != null) {
            stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled), normalDrawable)
        }
        val gray = getSolidRectDrawable(i_radius_size, i_enabled_color)
        if (gray != null) {
            stateListDrawable.addState(intArrayOf(), gray)
        }
        return stateListDrawable
    }

    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    private fun getSolidRectDrawableUnselected(cornerRadius: Int, solidColor: Int): GradientDrawable? {

        val gradientDrawable = GradientDrawable()
        // 设置矩形的圆角半径
        gradientDrawable.cornerRadius = cornerRadius.toFloat()
        // 设置绘画图片色值
        gradientDrawable.setColor(solidColor)
        gradientDrawable.setStroke(i_stroke_width_unselected, i_stroke_color_unselected)
        // 绘画的是矩形
        gradientDrawable.gradientType = GradientDrawable.RADIAL_GRADIENT
        return gradientDrawable
    }

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    private fun getStateListDrawableUnselected(pressedDrawable: Drawable?, normalDrawable: Drawable?): StateListDrawable? {
        if (pressedDrawable == null && normalDrawable == null) {
            return null
        }
        val stateListDrawable = StateListDrawable()
        if (pressedDrawable != null) {
            stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed), pressedDrawable)
        }
        if (normalDrawable != null) {
            stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled), normalDrawable)
        }
        val gray = getSolidRectDrawableUnselected(i_radius_size, i_enabled_color_unselected)
        if (gray != null) {
            stateListDrawable.addState(intArrayOf(), gray)
        }
        return stateListDrawable
    }

    /**
     * 是否选中
     *
     * @param selected
     */
    fun selectedState(selected: Boolean) {
        if (selected) {
            //不需要按下去的效果
            if (i_pressed_color == 0 && i_normal_color == 0 && i_enabled_color == 0) {
                setBackgroundDrawable(getSolidRectDrawable(i_radius_size, i_solid_color))
            } else {
                setBackgroundDrawable(getStateListDrawable(getSolidRectDrawable(i_radius_size, i_pressed_color), getSolidRectDrawable(i_radius_size, i_normal_color)))
            }
            if (i_text_color != 0) {
                setTextColor(i_text_color)
            }
        } else {
            //不需要按下去的效果
            if (i_pressed_color_unselected == 0 && i_normal_color_unselected == 0 && i_enabled_color_unselected == 0) {
                setBackgroundDrawable(getSolidRectDrawableUnselected(i_radius_size, i_solid_color_unselected))
            } else {
                setBackgroundDrawable(getStateListDrawableUnselected(getSolidRectDrawableUnselected(i_radius_size, i_pressed_color_unselected), getSolidRectDrawableUnselected(i_radius_size, i_normal_color_unselected)))
            }
            if (i_text_color_unselected != 0) {
                setTextColor(i_text_color_unselected)
            }
        }
        isSelectedState = selected
    }

    override fun onDraw(canvas: Canvas) {
        when (i_text_style) {
            1 -> {
                paint.strokeWidth = 1f
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.isAntiAlias = true
            }
            2 -> {
                paint.strokeWidth = 1.5f
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.isAntiAlias = true
            }
            3 -> {
                paint.strokeWidth = 1.8f
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.isAntiAlias = true
            }
        }
        super.onDraw(canvas)
    }
}