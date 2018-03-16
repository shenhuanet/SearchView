package com.shenhua.libs.searchveiw

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF


/**
 * Created by shenhua on 2018-03-16-0016.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class StrategyCircle2Line : BaseStrategy() {

    private var mRectF: RectF = RectF()
    private var mRectF2: RectF = RectF()
    private var cx: Int = 0
    private var cy: Int = 0
    private var cr: Int = 0
    private val sign = 0.707f
    private val tran = 120f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    }

    override fun onDraw(canvas: Canvas?, mPaint: Paint) {
        when (mState) {
            AnimState.NONE -> drawNormalView(canvas, mPaint)
            AnimState.START -> drawStartAnimView(canvas, mPaint)
            AnimState.STOP -> drawStopAnimView(canvas, mPaint)
        }
    }

    private fun drawNormalView(canvas: Canvas?, mPaint: Paint) {
        cr = getWidth() / 50
        cx = getWidth() / 2
        cy = getHeight() / 2
        mRectF.left = (cx - cr).toFloat()
        mRectF.right = (cx + cr).toFloat()
        mRectF.top = (cy - cr).toFloat()
        mRectF.bottom = (cy + cr).toFloat()

        mRectF2.left = (cx - 3 * cr).toFloat()
        mRectF2.right = (cx + 3 * cr).toFloat()
        mRectF2.top = (cy - 3 * cr).toFloat()
        mRectF2.bottom = (cy + 3 * cr).toFloat()

        canvas?.save()
        mPaint.reset()
        mPaint.color = Color.parseColor("#FF4081")
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 4f
        mPaint.style = Paint.Style.STROKE

        canvas?.rotate(45f, cx.toFloat(), cy.toFloat())
        canvas?.drawLine((cx + cr).toFloat(), cy.toFloat(), (cx + cr * 2).toFloat(), cy.toFloat(), mPaint)
        canvas?.drawArc(mRectF, 0f, 360f, false, mPaint)
        canvas?.drawArc(mRectF2, 0f, 360f, false, mPaint)
        canvas?.restore()
    }

    private fun drawStartAnimView(canvas: Canvas?, mPaint: Paint) {
        canvas?.save()
        canvas?.drawLine(mRectF.left + cr + cr * sign, mRectF.top + cr + cr * sign,
                mRectF.left + cr + 2 * cr * sign, mRectF.top + cr + 2 * cr * sign, mPaint)
        canvas?.drawArc(mRectF, 0f, 360f, false, mPaint)
        canvas?.drawArc(mRectF2, 90f, -360 * (1 - mPro), false, mPaint)
        if (mPro >= 0.7f) {
            canvas?.drawLine((1 - mPro + 0.7f) * (mRectF2.right - 3 * cr), mRectF2.bottom,
                    mRectF2.right - 3 * cr, mRectF2.bottom, mPaint)
        }
        canvas?.restore()
        mRectF.left = cx - cr + tran * mPro
        mRectF.right = cx + cr + tran * mPro
        mRectF2.left = cx - 3 * cr + tran * mPro
        mRectF2.right = cx + 3 * cr + tran * mPro
    }

    private fun drawStopAnimView(canvas: Canvas?, mPaint: Paint) {
        canvas?.save()
        if (mPro > 0.7) {
            mPaint.alpha = (mPro * 255).toInt()
            drawNormalView(canvas, mPaint)
        }
        canvas?.restore()
    }
}