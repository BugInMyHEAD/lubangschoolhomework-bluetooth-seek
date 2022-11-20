package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.logging

import android.util.Log
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger

object LoggerImpl : Logger {

    override fun errorFromRepository(repository: Any, t: Throwable) = e(
        uuid = "636a7246-b176-4e70-93a3-526c67a2db4a",
        message = """
            Error from repository "${repository::class}"
            ${t.stackTrace.contentToString()}
        """.trimIndent(),
    )

    override fun d(uuid: String, message: String) {
        Log.d(null, "UUID=$uuid\n$message")
    }

    override fun i(uuid: String, message: String) {
        Log.i(null, "UUID=$uuid\n$message")
    }

    override fun w(uuid: String, message: String) {
        Log.w(null, "UUID=$uuid\n$message")
    }

    override fun e(uuid: String, message: String) {
        Log.e(null, "UUID=$uuid\n$message")
    }

}