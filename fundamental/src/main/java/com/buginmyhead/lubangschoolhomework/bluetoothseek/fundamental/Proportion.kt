package com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental

@JvmInline
value class Proportion private constructor(val value: Float) {

    companion object {

        val ZERO = Proportion(0F)
        val ONE = Proportion(1F)

        private fun Float.isValidProportion(): Boolean = this in 0F .. 1F

        fun orNull(value: Float): Proportion? = when(value) {
            0F -> ZERO
            1F -> ONE
            else -> value.takeIf { it.isValidProportion() }?.let(::Proportion)
        }

    }

}