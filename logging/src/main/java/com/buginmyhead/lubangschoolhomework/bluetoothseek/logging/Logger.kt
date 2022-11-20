package com.buginmyhead.lubangschoolhomework.bluetoothseek.logging

interface Logger {

    fun errorFromRepository(repository:Any, t: Throwable)

    fun d(uuid: String, message: String)
    fun i(uuid: String, message: String)
    fun w(uuid: String, message: String)
    fun e(uuid: String, message: String)

}