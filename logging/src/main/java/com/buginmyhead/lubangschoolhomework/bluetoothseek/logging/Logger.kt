package com.buginmyhead.lubangschoolhomework.bluetoothseek.logging

interface Logger {

    fun errorFromRepository(repository:Any, t: Throwable)

    fun d(message: String)
    fun i(message: String)
    fun w(message: String)
    fun e(message: String)

}