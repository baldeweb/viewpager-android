package com.wallace.viewpagerandroid

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

open class AlternativeViewContainer(context: Context, attrs: AttributeSet, defStyle: Int) : FrameLayout(context, attrs, defStyle) {

    var defaultView: View? = null
        private set
    var alternativeView: View? = null
        private set

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    override fun getAccessibilityClassName(): CharSequence = AlternativeViewContainer::class.java.name

    override fun onFinishInflate() {
        super.onFinishInflate()

        when (childCount) {
            0, 1, 2 -> {
                defaultView = getChildAt(0)
                defaultView?.visibility = View.VISIBLE
                alternativeView = getChildAt(1)
                alternativeView?.visibility = View.GONE
            }
            else -> {
                throw IllegalStateException("The ${AlternativeViewContainer::class.simpleName} should only receive the default view and the alternative view")
            }
        }
    }
}