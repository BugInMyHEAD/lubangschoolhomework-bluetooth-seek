package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import io.reactivex.rxjava3.core.Observable

interface BluetoothSeekRepository {

    @Throws(NoBluetoothSupportException::class, BluetoothPermissionException::class, NoPairedDeviceException::class)
    fun anyPairInRange(): Observable<Boolean>

    @Throws(NoBluetoothSupportException::class, BluetoothPermissionException::class, NoPairedDeviceException::class)
    fun startDiscovery()

    @Throws(NoBluetoothSupportException::class, BluetoothPermissionException::class, NoPairedDeviceException::class)
    fun stopDiscovery()

}