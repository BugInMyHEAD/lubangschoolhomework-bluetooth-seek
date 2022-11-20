package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import androidx.core.content.getSystemService
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.Logger
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothPermissionException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothRadioOffException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.NoBluetoothSupportException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.NoPairedDeviceException
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.Collections
import javax.inject.Inject

class BluetoothSeekRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logger: Logger,
) : BluetoothSeekRepository {

    private val bluetoothManager: BluetoothManager? = context.getSystemService()

    private val _anyPairInRange = BehaviorSubject.create<Boolean>()
    override fun anyPairInRange(): Observable<Boolean> = _anyPairInRange

    private var isDiscovering = false

    private val gattConnections: MutableSet<BluetoothGatt> = Collections.synchronizedSet(mutableSetOf())

    override fun startDiscovery() {
        stopDiscovery()
        isDiscovering = true

        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()
        if (!adapter.isEnabled) throw BluetoothRadioOffException()

        val gattCallback = object : BluetoothGattCallback() {

            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                logger.d("c7b0d638-bd7d-4483-913e-4b2cc3f41e8b", "${gatt.device}, $status, $newState")
                try {
                    _anyPairInRange.onNext(
                        (bluetoothManager.getConnectedDevices(BluetoothProfile.GATT) intersect adapter.bondedDevices).isNotEmpty()
                    )
                    if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        gatt.close()
                        gattConnections -= gatt
                        Thread.sleep(5000L)
                        if (isDiscovering) {
                            gattConnections += gatt.device.connectGatt(context, false, this)
                        }
                    }
                } catch (exc: SecurityException) {
                    throw BluetoothPermissionException(exc)
                }
            }

        }

        try {
            val bondedDevices = adapter.bondedDevices
            if (bondedDevices.isEmpty()) throw NoPairedDeviceException()
            bondedDevices.forEach {
                gattConnections += it.connectGatt(context, false, gattCallback)
            }
        } catch (exc: SecurityException) {
            isDiscovering = false
            throw BluetoothPermissionException(exc)
        }
    }

    override fun stopDiscovery() {
        isDiscovering = false
        gattConnections.forEach {
            try {
                it.close()
            } catch (exc: SecurityException) {
                throw BluetoothPermissionException(exc)
            }
        }
        gattConnections.clear()
    }

}