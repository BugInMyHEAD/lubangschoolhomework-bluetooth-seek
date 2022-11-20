package com.buginmyhead.lubangschoolhomework.bluetoothseek.androidutility

import android.graphics.Canvas

val Canvas.centerX: Float get() = width / 2f
val Canvas.centerY: Float get() = height / 2f
val Canvas.short: Int get() = minOf(width, height)