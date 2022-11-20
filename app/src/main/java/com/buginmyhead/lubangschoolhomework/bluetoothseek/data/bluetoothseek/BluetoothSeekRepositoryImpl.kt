package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import androidx.core.content.getSystemService
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothPermissionException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.NoBluetoothSupportException
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BluetoothSeekRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logger: Logger,
) : BluetoothSeekRepository {

    private val bluetoothManager: BluetoothManager? = context.getSystemService()

    private val _anyPairInRange = BehaviorSubject.create<Boolean>()

    override fun anyPairInRange(): Observable<Boolean> = _anyPairInRange

    override fun startDiscovery() {
        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()

        val gattCallback = object : BluetoothGattCallback() {

            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                logger.d("c7b0d638-bd7d-4483-913e-4b2cc3f41e8b", "${gatt.device}, $status, $newState")
                try {
                    _anyPairInRange.onNext(
                        (bluetoothManager.getConnectedDevices(BluetoothProfile.GATT) intersect adapter.bondedDevices).isNotEmpty()
                    )
                    if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        Single.timer(5L, TimeUnit.SECONDS)
                            .subscribe(Consumer { gatt.device.connectGatt(context, false, this) })
                    }
                } catch (exc: SecurityException) {
                    throw BluetoothPermissionException(exc)
                }
            }

        }

        try {
            adapter.bondedDevices.forEach {
                it.connectGatt(context, false, gattCallback)
            }
        } catch (exc: SecurityException) {
            throw BluetoothPermissionException(exc)
        }
    }

    override fun stopDiscovery() {
        val adapter = bluetoothManager?.adapter ?: throw NoBluetoothSupportException()
        try {
            adapter.bondedDevices.forEach {
            }
        } catch (exc: SecurityException) {
            throw BluetoothPermissionException(exc)
        }
    }

}