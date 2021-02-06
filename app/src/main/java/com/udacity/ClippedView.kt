package com.udacity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private val path = Path()

    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)

    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    private val rectInset = resources.getDimension(R.dimen.rectInset)

    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight

    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircleOne(canvas)
        drawCircleTwo(canvas)
        drawCircleThree(canvas)
    }

    private fun drawCircle(canvas: Canvas?) {
//        canvas?.clipRect(
//            clipRectLeft, clipRectTop,
//            clipRectRight, clipRectBottom)
//
//        canvas?.drawColor(Color.WHITE)
        paint.color = resources.getColor(R.color.colorDownloadIcon, null)
        canvas?.drawCircle(
            circleRadius + 450, clipRectBottom - circleRadius - 20,
            circleRadius-5, paint
        )

        canvas?.drawCircle(
            circleRadius + 160, clipRectBottom - circleRadius + 60,
            circleRadius-10, paint
        )

        canvas?.drawCircle(
            circleRadius, clipRectBottom - circleRadius + 40,
            circleRadius, paint
        )
    }

    private fun drawCircleThree(canvas: Canvas?) {
    }

    private fun drawCircleTwo(canvas: Canvas?) {
    }

    private fun drawCircleOne(canvas: Canvas?) {
        canvas?.let {
            it.save()
            it.translate(columnOne, rowOne)
            drawCircle(it)
            it.restore()
        }
    }

}