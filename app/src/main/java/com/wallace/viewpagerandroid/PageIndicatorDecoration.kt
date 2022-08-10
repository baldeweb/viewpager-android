package com.wallace.viewpagerandroid

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageIndicatorDecoration(
    @ColorInt private val colorActive: Int,
    @ColorInt private val colorInactive: Int,
    diameter: Int = 8,
    paddingBetween: Int = 8,
    paddingBottom: Int = 18
) : RecyclerView.ItemDecoration() {

    private val radius: Float = (diameter * PaintCursor.DP) / 2f
    private val paddingBetween: Float = paddingBetween * PaintCursor.DP
    private val paddingBottom: Float = paddingBottom * PaintCursor.DP + this.radius
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter?.itemCount ?: return
        val cursor: PaintCursor = getIndicatorCursor(parent)
        val spacing: Float = paddingBetween + (2f * radius)
        val firstX = cursor.x?.minus(spacing * (itemCount - 1) / 2f)

        cursor.x = firstX
        for (i in 0 until itemCount) {
            draw(c, cursor, colorInactive)
            cursor.x = cursor.x?.plus(spacing)
        }

        val layoutManager = parent.layoutManager as LinearLayoutManager
        val activePosition = layoutManager.findFirstCompletelyVisibleItemPosition()

        if (activePosition != RecyclerView.NO_POSITION) {
            cursor.x = firstX?.plus(spacing * activePosition)
            draw(c, cursor, colorActive)
        }
    }

    private fun getIndicatorCursor(parent: RecyclerView): PaintCursor {
        val cursor = PaintCursor()
        cursor.x = parent.width / 2f
        cursor.y = parent.height - paddingBottom

        return cursor
    }

    private fun draw(c: Canvas, cursor: PaintCursor, color: Int) {
        paint.color = color
        c.drawCircle(cursor.x ?: 0F, cursor.y ?: 0F, radius, paint)
    }

    class PaintCursor(var x: Float? = 0F, var y: Float? = 0F) {
        companion object {
            val DP = Resources.getSystem().displayMetrics.density
        }

        constructor() : this(0f, 0f)
    }
}