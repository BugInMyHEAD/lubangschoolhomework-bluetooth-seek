package com.buginmyhead.lubangschoolhomework.bluetoothseek.app

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure

class DomainToResource private constructor(private val resources: Resources) {

    @StringRes
    fun stringResFromBluetoothSeekFailure(bluetoothSeekFailure: BluetoothSeekFailure?): Int = when (bluetoothSeekFailure) {
        null -> R.string.empty
        BluetoothSeekFailure.NO_PERMISSION -> R.string.no_permission
        BluetoothSeekFailure.UNSUPPORTED -> R.string.unsupported
        BluetoothSeekFailure.RADIO_OFF -> R.string.radio_off
        BluetoothSeekFailure.NO_PAIRED_DEVICES -> R.string.no_paired_devices
        BluetoothSeekFailure.UNKNOWN -> R.string.error
    }

    companion object {

        @JvmStatic
        fun from(context: Context) = DomainToResource(context.resources)
        @JvmStatic
        fun from(resources: Resources) = DomainToResource(resources)

    }

}
