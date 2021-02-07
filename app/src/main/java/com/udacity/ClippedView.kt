package com.udacity

import android.content.Context
import android.graphics.*
import android.os.Build
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
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDownloadLogo(canvas)
    }

    private fun drawDownloadLogo(canvas: Canvas) {

        val middleWidth = width.toFloat() / 2
        val middleHeight = height.toFloat() / 2

        canvas.drawColor(resources.getColor(R.color.colorPrimaryDark, null))

        paint.color = resources.getColor(R.color.colorDownloadIcon, null)

        canvas.drawRoundRect(middleWidth - 250,
                clipRectTop - 40,
                clipRectRight + 200,
                clipRectBottom,
                clipRectRight / 4, clipRectRight / 4, paint)

        canvas.drawRoundRect(middleWidth - 200 + clipRectRight,
                clipRectTop,
                clipRectRight,
                clipRectBottom,
                clipRectRight / 4,
                clipRectRight / 4,
                paint)

        canvas.drawCircle(middleWidth + 20, height.toFloat() / 2 - 50, circleRadius, paint)

        paint.color = resources.getColor(R.color.colorPrimaryDark, null)

        canvas.drawRect(
                middleWidth,  160f,
                middleWidth + 80, clipRectBottom - 120, paint)

        drawTriangle(canvas, middleWidth, middleHeight, paint)

    }

    private fun drawTriangle(canvas: Canvas, middleWidth: Float, middleHeight: Float, paint: Paint) {

        path.moveTo( middleWidth + 40, middleHeight + 100)
        path.lineTo(middleWidth - 50, middleHeight)
        path.lineTo(middleWidth + 130, middleHeight)
        path.lineTo(middleWidth + 40, middleHeight + 100)
        path.close()

        canvas.drawPath(path, paint);
    }
}