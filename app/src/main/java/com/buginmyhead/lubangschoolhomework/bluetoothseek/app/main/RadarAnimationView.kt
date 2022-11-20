package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import com.buginmyhead.lubangschoolhomework.bluetoothseek.androidutility.centerX
import com.buginmyhead.lubangschoolhomework.bluetoothseek.androidutility.centerY
import com.buginmyhead.lubangschoolhomework.bluetoothseek.androidutility.short
import com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental.Proportion

class RadarAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = ResourcesCompat.ID_NULL,
    @StyleRes defStyleRes: Int = ResourcesCompat.ID_NULL
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint()

    private var progress = Proportion.ONE
        set(proportion) {
            field = proportion
            invalidate()
        }

    @ColorInt
    var color: Int = Color.TRANSPARENT
        set(@ColorInt value) {
            field = value
            paint.color = value
            invalidate()
        }

    private val progressUpdateListener = ValueAnimator.AnimatorUpdateListener {
        val progress = Proportion.orNull(it.animatedValue as? Float ?: return@AnimatorUpdateListener) ?: return@AnimatorUpdateListener
        this@RadarAnimationView.progress = progress
    }

    private val animator = ValueAnimator.ofFloat(Proportion.ZERO.value, Proportion.ONE.value).apply {
        duration = 5000L
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener(progressUpdateListener)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        paint.alpha = (progress.inverted().value * 0xFF).toInt()
        val radius = progress.value * canvas.short / 2f
        drawCircle(centerX, centerY, radius, paint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        animator.cancel()
        super.onDetachedFromWindow()
    }

}