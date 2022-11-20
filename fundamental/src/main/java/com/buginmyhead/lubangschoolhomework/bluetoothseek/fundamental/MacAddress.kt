package com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental

@JvmInline
value class MacAddress private constructor(val value: Long) {

    override fun toString(): String = (0 .. 5)
        .map { (value ushr Byte.SIZE_BITS * it) and 0xFFL }
        .joinToString(":") { "%02X".format(it) }

    companion object {

        fun fromString(s: String): MacAddress? = runCatching {
            s.split(':')
                .takeIf { it.size == 6 }!!
                .mapIndexed { i, v -> (v.toLong(0x10) and 0xFFL) shl Byte.SIZE_BITS * i }
                .fold(0L) { acc, v -> acc or v }
                .let(::MacAddress)
        }.getOrNull()

    }

}