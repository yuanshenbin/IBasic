package com.yuanshenbin.basic.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.yuanshenbin.basic.R;


/**
 * author : yuanshenbin
 * time   : 2018/12/10
 * desc   :
 */

public class ILinearLayout extends LinearLayout {

    private int i_ll_pressed_color;//按下的颜色

    private int i_ll_normal_color;//正常状态颜色

    private int i_ll_enabled_color;//不可用的颜色

    private int i_ll_stroke_color;//边线的颜色

    private int i_ll_solid_color;//填充颜色

    @DimenRes
    private int i_ll_radius_size;//弧度大小

    @DimenRes
    private int i_ll_stroke_width;//边线的宽

    /**
     * 未选中
     */
    private int i_ll_pressed_color_unselected;//未选中按下的颜色

    private int i_ll_normal_color_unselected;//未选中正常状态颜色

    private int i_ll_enabled_color_unselected;//未选中不可用的颜色

    private int i_ll_stroke_color_unselected;//未选中边线的颜色

    private int i_ll_solid_color_unselected;//未选中填充颜色

    @DimenRes
    private int i_ll_stroke_width_unselected;//未选中边线的宽


    /**
     * 是否选中状态
     */
    private boolean i_ll_selected;


    public ILinearLayout(Context context) {
        super(context);
    }

    public ILinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ILinearLayout);

            i_ll_selected = ta.getBoolean(R.styleable.ILinearLayout_i_ll_selected, true);
            i_ll_normal_color = ta.getColor(R.styleable.ILinearLayout_i_ll_normal_color, 0);
            i_ll_enabled_color = ta.getColor(R.styleable.ILinearLayout_i_ll_enabled_color, 0);
            i_ll_stroke_color = ta.getColor(R.styleable.ILinearLayout_i_ll_stroke_color, 0);
            i_ll_solid_color = ta.getColor(R.styleable.ILinearLayout_i_ll_solid_color, 0);
            i_ll_pressed_color = ta.getColor(R.styleable.ILinearLayout_i_ll_pressed_color, 0);
            i_ll_radius_size = (int) ta.getDimension(R.styleable.ILinearLayout_i_ll_radius_size, 0);
            i_ll_stroke_width = (int) ta.getDimension(R.styleable.ILinearLayout_i_ll_stroke_width, 0);

            i_ll_pressed_color_unselected = ta.getColor(R.styleable.ILinearLayout_i_ll_pressed_color_unselected, 0);
            i_ll_normal_color_unselected = ta.getColor(R.styleable.ILinearLayout_i_ll_normal_color_unselected, 0);
            i_ll_enabled_color_unselected = ta.getColor(R.styleable.ILinearLayout_i_ll_enabled_color_unselected, 0);
            i_ll_stroke_color_unselected = ta.getColor(R.styleable.ILinearLayout_i_ll_stroke_color_unselected, 0);
            i_ll_solid_color_unselected = ta.getColor(R.styleable.ILinearLayout_i_ll_solid_color_unselected, 0);
            i_ll_stroke_width_unselected = (int) ta.getDimension(R.styleable.ILinearLayout_i_ll_stroke_width_unselected, 0);
            ta.recycle();
        }
        selectedState(i_ll_selected);

    }

    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    private GradientDrawable getSolidRectDrawable(int cornerRadius, int solidColor) {
        if (solidColor == 0)
            return null;
        GradientDrawable gradientDrawable = new GradientDrawable();
        // 设置矩形的圆角半径
        gradientDrawable.setCornerRadius(cornerRadius);
        // 设置绘画图片色值

        gradientDrawable.setColor(solidColor);
        gradientDrawable.setStroke(i_ll_stroke_width, i_ll_stroke_color);
        // 绘画的是矩形
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        return gradientDrawable;
    }

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    private StateListDrawable getStateListDrawable(Drawable pressedDrawable, Drawable normalDrawable) {
        if (pressedDrawable == null && normalDrawable == null) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (pressedDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressedDrawable);
        }
        if (normalDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        }
        GradientDrawable gray = getSolidRectDrawable(i_ll_radius_size, i_ll_enabled_color);
        if (gray != null) {
            stateListDrawable.addState(new int[]{}, gray);
        }
        return stateListDrawable;
    }


    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    private GradientDrawable getSolidRectDrawableUnselected(int cornerRadius, int solidColor) {
        if (solidColor == 0) {
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        // 设置矩形的圆角半径
        gradientDrawable.setCornerRadius(cornerRadius);
        // 设置绘画图片色值
        if (solidColor != 0) {
            gradientDrawable.setColor(solidColor);
        }
        gradientDrawable.setStroke(i_ll_stroke_width_unselected, i_ll_stroke_color_unselected);
        // 绘画的是矩形
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        return gradientDrawable;
    }

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    private StateListDrawable getStateListDrawableUnselected(Drawable pressedDrawable, Drawable normalDrawable) {
        if (pressedDrawable == null && normalDrawable == null) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (pressedDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressedDrawable);
        }
        if (normalDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        }
        GradientDrawable gray = getSolidRectDrawableUnselected(i_ll_radius_size, i_ll_enabled_color_unselected);
        if (gray != null) {
            stateListDrawable.addState(new int[]{}, gray);
        }
        return stateListDrawable;
    }


    /**
     * 是否选中
     *
     * @param selected
     */
    public void selectedState(boolean selected) {
        if (selected) {
            //不需要按下去的效果
            if (i_ll_pressed_color == 0 && i_ll_normal_color == 0 && i_ll_enabled_color == 0) {
                setBackgroundDrawable(getSolidRectDrawable(i_ll_radius_size, i_ll_solid_color));
            } else {
                setBackgroundDrawable(getStateListDrawable(getSolidRectDrawable(i_ll_radius_size, i_ll_pressed_color), getSolidRectDrawable(i_ll_radius_size, i_ll_normal_color)));
            }
        
        } else {
            //不需要按下去的效果
            if (i_ll_pressed_color_unselected == 0 && i_ll_normal_color_unselected == 0 && i_ll_enabled_color_unselected == 0) {
                setBackgroundDrawable(getSolidRectDrawableUnselected(i_ll_radius_size, i_ll_solid_color_unselected));
            } else {
                setBackgroundDrawable(getStateListDrawableUnselected(getSolidRectDrawableUnselected(i_ll_radius_size, i_ll_pressed_color_unselected), getSolidRectDrawableUnselected(i_ll_radius_size, i_ll_normal_color_unselected)));
            }
         
        }
        i_ll_selected =selected;
    }

    /**
     * 获取选中状态
     * @return
     */
    public  boolean isSelectedState(){
        return  i_ll_selected;
    }
}
