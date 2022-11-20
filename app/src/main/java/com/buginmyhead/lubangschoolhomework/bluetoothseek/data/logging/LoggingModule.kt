package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.logging

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggingModuleProvider {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl

}