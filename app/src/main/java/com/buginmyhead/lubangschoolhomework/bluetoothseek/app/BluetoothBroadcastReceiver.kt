package com.buginmyhead.lubangschoolhomework.bluetoothseek.app

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.Logger
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BluetoothBroadcastReceiver : BroadcastReceiver() {

    @Inject
    internal lateinit var logger: Logger

    @Inject
    internal lateinit var bluetoothSeekUseCase: BluetoothSeekUseCase

    override fun onReceive(context: Context, intent: Intent) {
        logger.d("896f9d1b-9420-47e1-a090-fca65d86d844", "${intent.action}, ${intent.extras}")
        when (intent.action) {
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                bluetoothSeekUseCase.start()
            }
        }
    }

    companion object {

        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)

    }

}