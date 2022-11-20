package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek

import androidx.annotation.RequiresPermission
import com.buginmyhead.lubangschoolhomework.bluetoothseek.fundamental.MacAddress

@RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
fun android.bluetooth.BluetoothDevice.toDomainBluetoothDevice(
) = com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothDevice(
    macAddress = MacAddress.fromString(address)!!,
    name = name,
)