package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.logging

import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoggingModuleBinder {

    @Binds
    @Singleton
    fun bindsLogger(
        impl: LoggerImpl
    ): Logger

}