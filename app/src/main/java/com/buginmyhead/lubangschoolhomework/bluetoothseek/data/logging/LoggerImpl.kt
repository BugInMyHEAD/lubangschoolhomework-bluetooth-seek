package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.logging

import android.util.Log
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import javax.inject.Inject

class LoggerImpl @Inject constructor() : Logger {

    override fun errorFromRepository(repository: Any, t: Throwable) = e(
        """
        Error from repository "${repository::class}"
        ${t.stackTrace.contentToString()}
        """.trimIndent()
    )

    override fun d(message: String) {
        Log.d(null, message)
    }

    override fun i(message: String) {
        Log.i(null, message)
    }

    override fun w(message: String) {
        Log.w(null, message)
    }

    override fun e(message: String) {
        Log.e(null, message)
    }

}