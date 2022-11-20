package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import io.reactivex.rxjava3.core.Single

interface BluetoothSeekRepository {

    @Throws(BluetoothPermissionException::class)
    fun isAnyPairInRange(): Single<Boolean>

}