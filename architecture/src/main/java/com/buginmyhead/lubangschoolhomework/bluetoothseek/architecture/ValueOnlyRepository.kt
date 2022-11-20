package com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture

import io.reactivex.rxjava3.core.Single

interface ValueOnlyRepository<T : Any> {

    val value: Single<T>

}