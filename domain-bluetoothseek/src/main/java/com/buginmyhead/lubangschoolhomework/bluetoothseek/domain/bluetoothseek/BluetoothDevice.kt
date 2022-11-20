package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental.MacAddress

data class BluetoothDevice(
    val macAddress: MacAddress,
    val name: String?,
) {

    override fun equals(other: Any?): Boolean = other is BluetoothDevice && macAddress == other.macAddress

    override fun hashCode(): Int = macAddress.hashCode()

}