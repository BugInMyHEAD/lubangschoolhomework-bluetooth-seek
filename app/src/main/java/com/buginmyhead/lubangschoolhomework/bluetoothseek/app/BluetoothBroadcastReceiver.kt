package com.buginmyhead.lubangschoolhomework.bluetoothseek.app

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.RequiresPermission
import com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.toDomainBluetoothDevice
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger

class BluetoothBroadcastReceiver(
    private val bluetoothSeekRepository: BluetoothSeekRepository,
    private val logger: Logger,
) : BroadcastReceiver() {

    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == BluetoothDevice.ACTION_FOUND) {
            val androidBluetoothDevice: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            logger.d("fb71e74a-28cc-46d7-9044-b3d1b023dd28", androidBluetoothDevice.toString())
            logger.d("93d489bc-5eb4-4f96-ab0b-6b98a1617737", androidBluetoothDevice?.toDomainBluetoothDevice().toString())
            androidBluetoothDevice?.toDomainBluetoothDevice()?.let { bluetoothSeekRepository.addDiscoveredDevice(it) }
        }
    }

    companion object {

        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)

    }

}