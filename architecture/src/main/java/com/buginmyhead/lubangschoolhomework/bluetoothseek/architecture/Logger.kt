package com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture

interface Logger {

    fun errorFromRepository(repository:Any, t: Throwable)

    fun d(uuid: String, message: Any?)
    fun i(uuid: String, message: Any?)
    fun w(uuid: String, message: Any?)
    fun e(uuid: String, message: Any?)

}