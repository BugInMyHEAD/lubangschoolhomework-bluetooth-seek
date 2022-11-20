package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek

import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BluetoothSeekRepositoryImpl @Inject constructor() : BluetoothSeekRepository {

    override fun isAnyPairInRange(): Single<Boolean> = Single.just(true)

}