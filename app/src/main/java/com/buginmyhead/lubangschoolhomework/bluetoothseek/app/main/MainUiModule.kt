package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainUiModuleProvider {

    @Provides
    @Singleton
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekViewController(
    ): ViewController<Boolean, Unit, BluetoothSeekFailure> = object : AbstractViewController<Boolean, Unit, BluetoothSeekFailure>() {

        override val output = PublishSubject.create<ViewState<Boolean, Unit, BluetoothSeekFailure>>()

    }

    @Provides
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekOutput(
        @BluetoothSeekQualifier.MainView
        viewController: ViewController<Boolean, Unit, BluetoothSeekFailure>
    ): Observable<ViewState<Boolean, Unit, BluetoothSeekFailure>> =
        viewController.output

}