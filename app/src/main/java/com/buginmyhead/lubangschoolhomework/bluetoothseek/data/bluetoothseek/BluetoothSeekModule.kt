package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.di

import com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.BluetoothSeekRepositoryImpl
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BluetoothSeekModuleBinder {

    @Binds
    @Singleton
    fun bindBluetoothSeekRepository(
        impl: BluetoothSeekRepositoryImpl
    ): BluetoothSeekRepository

}

