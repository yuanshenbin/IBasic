package com.yuanshenbin.basic.widget

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.yuanshenbin.basic.R
import com.yuanshenbin.basic.util.DisplayUtils.Companion.dip2px

class MiniLoadingWidget : View {
    private var mSize = 0
    private var mPaintColor = 0
    private var mAnimateValue = 0
    private var mAnimator: ValueAnimator? = null
    private var mPaint: Paint? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.MiniLoadingStyle) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs, defStyleAttr)
        initPaint()
    }

    constructor(context: Context?, size: Int, color: Int) : super(context) {
        mSize = size
        mPaintColor = color
        initPaint()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val array = getContext().obtainStyledAttributes(attrs, R.styleable.MiniLoadingView, defStyleAttr, 0)
        mSize = array.getDimensionPixelSize(R.styleable.MiniLoadingView_mlv_loading_view_size, dip2px(context, 32f))
        mPaintColor = array.getColor(R.styleable.MiniLoadingView_mlv_loading_view_color, Color.WHITE)
        array.recycle()
    }

    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.color = mPaintColor
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeCap = Paint.Cap.ROUND
    }

    fun setColor(color: Int) {
        mPaintColor = color
        mPaint!!.color = color
        invalidate()
    }

    fun setSize(size: Int) {
        mSize = size
        requestLayout()
    }

    private val mUpdateListener = AnimatorUpdateListener { animation ->
        mAnimateValue = animation.animatedValue as Int
        invalidate()
    }

    fun start() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofInt(0, LINE_COUNT - 1)
            mAnimator?.addUpdateListener(mUpdateListener)
            mAnimator?.setDuration(600)
            mAnimator?.setRepeatMode(ValueAnimator.RESTART)
            mAnimator?.setRepeatCount(ValueAnimator.INFINITE)
            mAnimator?.setInterpolator(LinearInterpolator())
            mAnimator?.start()
        } else if (!mAnimator!!.isStarted) {
            mAnimator!!.start()
        }
    }

    fun stop() {
        if (mAnimator != null) {
            mAnimator!!.removeUpdateListener(mUpdateListener)
            mAnimator!!.removeAllUpdateListeners()
            mAnimator!!.cancel()
            mAnimator = null
        }
    }

    private fun drawLoading(canvas: Canvas, rotateDegrees: Int) {
        val width = mSize / 12
        val height = mSize / 6
        mPaint!!.strokeWidth = width.toFloat()
        canvas.rotate(rotateDegrees.toFloat(), mSize / 2.toFloat(), mSize / 2.toFloat())
        canvas.translate(mSize / 2.toFloat(), mSize / 2.toFloat())
        for (i in 0 until LINE_COUNT) {
            canvas.rotate(DEGREE_PER_LINE.toFloat())
            mPaint!!.alpha = (255f * (i + 1) / LINE_COUNT).toInt()
            canvas.translate(0f, -mSize / 2 + width / 2.toFloat())
            canvas.drawLine(0f, 0f, 0f, height.toFloat(), mPaint!!)
            canvas.translate(0f, mSize / 2 - width / 2.toFloat())
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mSize, mSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        drawLoading(canvas, mAnimateValue * DEGREE_PER_LINE)
        canvas.restoreToCount(saveCount)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            start()
        } else {
            stop()
        }
    }

    companion object {
        private const val LINE_COUNT = 12
        private const val DEGREE_PER_LINE = 360 / LINE_COUNT
    }
}