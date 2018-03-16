package com.shenhua.libs.searchveiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Created by shenhua on 2018-03-16-0016.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class SearchView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attr, defStyleAttr) {

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPath: Path
    private var mStrategy: BaseStrategy? = null

    init {
        mPaint.strokeWidth = 4f
        mPath = Path()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mStrategy?.onDraw(canvas, mPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mStrategy?.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun setStrategy(baseStrategy: BaseStrategy) {
        mStrategy = baseStrategy
        baseStrategy.setSearchView(this)
        invalidate()
    }

    fun startAnim() {
        mStrategy?.startAnim()
    }

    fun resetAnim() {
        mStrategy?.resetAnim()
    }

}