package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek

import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.core.content.getSystemService
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.BluetoothBroadcastReceiver
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothDevice
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothPermissionException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.NoBluetoothSupportException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.NoPairedDeviceException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class BluetoothSeekRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logger: Logger,
) : BluetoothSeekRepository {

    private val bluetoothManager: BluetoothManager? = context.getSystemService()

    private val broadcastReceiver = BluetoothBroadcastReceiver(this, logger)

    private var devices = listOf<NearbyDevice>()
        get() = field
            .filter { currentTime() - 30.seconds <= it.discoveredOn }
            .also { field = it }

    override fun isAnyPairInRange(): Single<Boolean> {
        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()
        return try {
            adapter.startDiscovery()
            val pairedDevices = adapter.bondedDevices.map { it.toDomainBluetoothDevice() }.toSet()
            if (pairedDevices.isEmpty()) throw NoPairedDeviceException()
            val nearbyDevices = devices.map(NearbyDevice::device).toSet()
            Single.just((pairedDevices intersect nearbyDevices).isNotEmpty())
        } catch (exc: SecurityException) {
            throw BluetoothPermissionException(exc)
        }
    }

    override fun startDiscovery() {
        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()
        try {
            context.registerReceiver(broadcastReceiver, BluetoothBroadcastReceiver.intentFilter)
            adapter.startDiscovery()
        } catch (exc: SecurityException) {
            throw BluetoothPermissionException(exc)
        }
    }

    override fun stopDiscovery() {
        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()
        try {
            adapter.cancelDiscovery()
            context.unregisterReceiver(broadcastReceiver)
        } catch (exc: SecurityException) {
            throw BluetoothPermissionException(exc)
        }
    }

    override fun addDiscoveredDevice(device: BluetoothDevice) {
        devices += NearbyDevice(device, currentTime())
    }

    private fun currentTime(): Duration = System.currentTimeMillis().milliseconds

    private data class NearbyDevice(
        val device: BluetoothDevice,
        val discoveredOn: Duration,
    )

}