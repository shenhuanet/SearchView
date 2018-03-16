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
class StrategyCircle2Bar : BaseStrategy() {

    private var cx: Float = 0f
    private var cy: Float = 0f
    private var cr: Float = 0f
    private val sign = 0.707f
    private val mCircleBig = 10f
    private var mRectF: RectF = RectF()
    private var mRectF2: RectF = RectF()
    private val mCirCleDis = 200f
    private var mFontPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mFontPaint.strokeWidth = 1f
        mFontPaint.color = Color.parseColor("#FF4081")
        mFontPaint.style = Paint.Style.FILL
        mFontPaint.textSize = 40f
    }

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
        cr = getWidth() / 15f
        cx = getWidth() / 2f
        cy = getHeight() / 2f
        mRectF.top = cy - cr - mCircleBig
        mRectF.bottom = cy + cr + mCircleBig
        mRectF2.top = cy - cr - mCircleBig
        mRectF2.bottom = cy + cr + mCircleBig

        mPaint.reset()
        mPaint.isAntiAlias = true
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas!!.save()
        mPaint.color = Color.parseColor("#FF4081")
        mPaint.strokeWidth = 4f
        mPaint.style = Paint.Style.STROKE
        canvas.drawCircle(cx, cy, cr, mPaint)
        canvas.drawLine(cx + cr * sign, cy + cr * sign, cx + cr * 2 * sign,
                cy + cr * 2 * sign, mPaint)
        canvas.restore()
    }

    private fun drawStartAnimView(canvas: Canvas?, mPaint: Paint) {
        canvas!!.save()

        if (mPro <= 0.1f) {
            canvas.drawLine(cx + cr * sign, cy + cr * sign, cx + cr * sign + cr * sign *
                    (1 - mPro * 10), cy + cr * sign + cr * sign * (1 - mPro * 10), mPaint)
            canvas.drawCircle(cx, cy, cr, mPaint)
        } else if (mPro > 0.1f && mPro <= 0.2) {
            canvas.drawCircle(cx, cy, cr + (mPro - 0.1f) * mCircleBig * 10, mPaint)
        } else if (mPro > 0.2 && mPro <= 0.3) {
            mRectF.left = cx - cr - mCircleBig + mCirCleDis * (mPro - 0.2f) * 10
            mRectF.right = cx + cr + mCircleBig + mCirCleDis * (mPro - 0.2f) * 10
            canvas.drawArc(mRectF, 0f, 360f, false, mPaint)
        } else if (mPro > 0.3 && mPro <= 0.4) {
            mRectF2.left = cx - cr - mCircleBig + mCirCleDis * (1 - (mPro - 0.3f) * 10)
            mRectF2.right = cx + cr + mCircleBig + mCirCleDis * (1 - (mPro - 0.3f) * 10)
            canvas.drawArc(mRectF, 90f, -180f, false, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.top, mRectF.right - cr - mCircleBig, mRectF.top, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.bottom, mRectF.right - cr - mCircleBig, mRectF.bottom, mPaint)
            canvas.drawArc(mRectF2, 90f, 180f, false, mPaint)
        } else if (mPro > 0.4 && mPro <= 0.5) {
            mRectF2.left = cx - cr - mCircleBig - mCirCleDis * (mPro - 0.4f) * 10
            mRectF2.right = cx + cr + mCircleBig - mCirCleDis * (mPro - 0.4f) * 10
            canvas.drawArc(mRectF, 90f, -180f, false, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.top, mRectF.right - cr - mCircleBig, mRectF.top, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.bottom, mRectF.right - cr - mCircleBig, mRectF.bottom, mPaint)
            canvas.drawArc(mRectF2, 90f, 180f, false, mPaint)
        } else if (mPro > 0.5 && mPro <= 0.6) {
            canvas.drawArc(mRectF, 90f, -180f, false, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.top, mRectF.right - cr - mCircleBig, mRectF.top, mPaint)
            canvas.drawLine(mRectF2.left + cr + mCircleBig, mRectF.bottom, mRectF.right - cr - mCircleBig, mRectF.bottom, mPaint)
            canvas.drawArc(mRectF2, 90f, 180f, false, mPaint)

            if (mPro > 0.5f && mPro <= 0.52f) {
                canvas.drawText("A", cx - mCirCleDis, cy + cr / 2, mFontPaint)
            } else if (mPro > 0.52 && mPro <= 0.53f) {
                canvas.drawText("AA", cx - mCirCleDis, cy + cr / 2, mFontPaint)
            } else if (mPro > 0.53 && mPro <= 0.54f) {
                canvas.drawText("AAAA", cx - mCirCleDis, cy + cr / 2, mFontPaint)
            } else if (mPro > 0.54 && mPro <= 0.55f) {
                canvas.drawText("AAAA BBBB", cx - mCirCleDis, cy + cr / 2, mFontPaint)
            } else {
                canvas.drawText("AAAA BBBB CCCC", cx - mCirCleDis, cy + cr / 2, mFontPaint)
            }
        } else if (mPro > 0.6 && mPro <= 0.7) {
            canvas.drawCircle(cx, cy, cr + mCircleBig, mPaint)
            canvas.drawLine(cx - cr / 2 + 4, cy + cr / 2, cx - cr / 2 + 4 - cr / 2, cy - cr / 2 + 8, mPaint)
            canvas.drawLine(cx - cr / 2 + 4, cy + cr / 2, cx + cr - 4, cy - cr / 2, mPaint)
        } else {
            canvas.drawCircle(cx, cy, cr + mCircleBig, mPaint)
            canvas.drawText("BUG", cx - cr / 2 - 8, cy + cr / 2, mFontPaint)
        }

        canvas.restore()
    }

    private fun drawStopAnimView(canvas: Canvas?, mPaint: Paint) {
        drawNormalView(canvas, mPaint)
    }

    override fun startAnim() {
        if (mState == AnimState.START) return
        mState = AnimState.START
        startSearchViewAnim(0f, 1f, 3000)
    }

}
