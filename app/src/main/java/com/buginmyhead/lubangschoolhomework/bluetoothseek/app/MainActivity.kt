package com.buginmyhead.lubangschoolhomework.bluetoothseek.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main.MainFragment
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var bluetoothSeekUseCase: BluetoothSeekUseCase

    private val bluetoothBroadcastReceiver = BluetoothBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        bluetoothSeekUseCase.start()
        registerReceiver(bluetoothBroadcastReceiver, BluetoothBroadcastReceiver.intentFilter)
    }

    override fun onStop() {
        unregisterReceiver(bluetoothBroadcastReceiver)
        bluetoothSeekUseCase.stop()
        super.onStop()
    }

}