package com.wallace.viewpagerandroid

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewContainer(context: Context, attrs: AttributeSet, defStyle: Int) : AlternativeViewContainer(context, attrs, defStyle) {

    var recyclerView: RecyclerView? = null
        private set

    private val mCenter = Point()
    private val mInitialTouch = Point()

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    init {
        clipChildren = false
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        try {
            recyclerView = super.defaultView as RecyclerView
        } catch (e: Exception) {
            throw IllegalStateException("The default child of RecyclerViewContainer must be a RecyclerView")
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mCenter.x = w / 2
        mCenter.y = h / 2
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mInitialTouch.x = ev.x.toInt()
                mInitialTouch.y = ev.y.toInt()
                ev.offsetLocation(mCenter.x - ev.x, mCenter.y - ev.y)
            }
            else -> ev.offsetLocation(mCenter.x - ev.x, mCenter.y - ev.y)
        }

        return recyclerView!!.dispatchTouchEvent(ev)
    }
}