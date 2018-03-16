package com.shenhua.libs.searchveiw

import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PathMeasure
import android.support.annotation.IntDef
import android.view.View
import android.view.animation.LinearInterpolator
import java.lang.ref.WeakReference


/**
 * Created by shenhua on 2018-03-16-0016.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
abstract class BaseStrategy {

    @IntDef(AnimState.NONE, AnimState.START, AnimState.STOP)
    @Retention(AnnotationRetention.SOURCE)
    annotation class State

    @JvmField
    protected var mPro = -1f
    private var mSearchView: WeakReference<View>? = null
    private var mPos = FloatArray(2)
    @State
    protected var mState: Long = AnimState.NONE

    abstract fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int)

    abstract fun onDraw(canvas: Canvas?, mPaint: Paint)

    fun getWidth(): Int {
        return if (mSearchView == null) 0 else mSearchView?.get()!!.width
    }

    fun getHeight(): Int {
        return if (mSearchView == null) 0 else mSearchView?.get()!!.height
    }

    open fun startAnim() {
        if (mState == AnimState.START) return
        mState = AnimState.START
        startSearchViewAnim()
    }

    open fun resetAnim() {
        if (mState == AnimState.STOP) return
        mState = AnimState.STOP
        startSearchViewAnim()
    }

    fun setSearchView(searchView: SearchView) {
        mSearchView = WeakReference(searchView)
    }

    fun startSearchViewAnim(): ValueAnimator {
        return startSearchViewAnim(0f, 1f, 500)
    }

    fun startSearchViewAnim(startF: Float, endF: Float, time: Long): ValueAnimator {
        return startSearchViewAnim(startF, endF, time, null)
    }

    fun startSearchViewAnim(startF: Float, endF: Float, time: Long, pathMeasure: PathMeasure?): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(startF, endF)
        valueAnimator.duration = time
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { _ ->
            mPro = valueAnimator.animatedValue as Float
            pathMeasure?.getPosTan(mPro, mPos, null)
            mSearchView?.get()?.invalidate()
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
        })
        if (!valueAnimator.isRunning) {
            valueAnimator.start()
        }
        mPro = 0f
        return valueAnimator
    }
}