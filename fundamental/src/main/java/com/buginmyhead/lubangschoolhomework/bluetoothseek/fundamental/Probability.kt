package com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental

@JvmInline
value class Probability private constructor(val value: Float) {

    companion object {

        private fun Float.isValidForProbability(): Boolean = this in 0F .. 1F

        fun orNull(value: Float): Probability? = value.takeIf { it.isValidForProbability() }?.let(::Probability)

    }

}