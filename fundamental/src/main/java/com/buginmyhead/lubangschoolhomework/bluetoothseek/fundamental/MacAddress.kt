package com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental

@JvmInline
value class MacAddress private constructor(val value: Long) {

    companion object {

        fun fromString(s: String): MacAddress? = runCatching {
            s.split(':')
                .takeIf { it.size == 6 }!!
                .mapIndexed { i, v -> v.toByte(0x10).toLong() shl i }
                .fold(0L) { acc, v -> acc or v }
                .let(::MacAddress)
        }.getOrNull()

    }

}