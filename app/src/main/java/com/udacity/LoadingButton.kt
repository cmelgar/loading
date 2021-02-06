package com.udacity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0
    private var angle = 0F

    private val paint = Paint().apply {
        isAntiAlias = true
        textSize = 64F
        textAlign = Paint.Align.CENTER
    }

    private var loadingButton: LoadingButton = findViewById(R.id.custom_button)

    init {
        isClickable =  true
    }

    private val valueAnimator = ValueAnimator()

    var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->
        when (new) {
            ButtonState.Loading -> {
                rotateCircle()
                animatedButton()
            }
        }
    }

    private fun animatedButton() {
        val buttonAnimator = ValueAnimator.ofFloat(0F, widthSize.toFloat()).apply {
            duration = 1000
            addUpdateListener { valueAnimator ->
                val progress = valueAnimator.animatedValue as Float
                valueAnimator.repeatCount = ValueAnimator.INFINITE
                valueAnimator.repeatMode = ValueAnimator.REVERSE
                valueAnimator.interpolator = LinearInterpolator()
                this@LoadingButton.invalidate()
            }
        }

        buttonAnimator.start()
    }

    private fun rotateCircle() {
        ValueAnimator.ofFloat(0F, 360F).apply {
            duration = 1000
            addUpdateListener { valueAnimator ->
                angle = valueAnimator.animatedValue as Float
                valueAnimator.repeatCount = 0
                valueAnimator.repeatMode = ValueAnimator.REVERSE
                loadingButton.invalidate()
            }
            start()
        }
    }

    private fun drawCircle(canvas: Canvas?) {
        paint.color= Color.YELLOW
        canvas?.drawArc((widthSize.toFloat() / 2 + 160f),(heightSize.toFloat() / 2) - 30f, (widthSize.toFloat() / 2 + 210f),
                (heightSize.toFloat() / 2) + 20f, 0F,angle, true,paint)
    }

    private fun ValueAnimator.disableWhileDownloading(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 50.0f
        color = resources.getColor(R.color.white, null)
    }

    private val textHeight: Float = paintText.descent() - paintText.ascent()
    private val textOffset: Float = textHeight / 2 - paintText.descent()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {

            if (buttonState == ButtonState.Loading) {
                it.drawText(resources.getString(R.string.loading_text),
                        (widthSize / 2).toFloat(),
                        (heightSize / 2 + textOffset),
                        paintText)
                drawCircle(canvas)
            } else {
                it.drawText(
                        resources.getString(R.string.button_text),
                        (widthSize / 2).toFloat(),
                        (heightSize / 2 + textOffset),
                        paintText)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

}