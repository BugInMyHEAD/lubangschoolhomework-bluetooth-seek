package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main.di

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


@Module
@InstallIn(ViewModelComponent::class)
object MainUiModuleProvider {

    @Provides
    @ViewModelScoped
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekViewController(
    ): ViewController<Boolean, Unit, BluetoothSeekFailure> = object : AbstractViewController<Boolean, Unit, BluetoothSeekFailure>() {

        override val output = PublishSubject.create<ViewState<Boolean, Unit, BluetoothSeekFailure>>()

    }

    @Provides
    @ViewModelScoped
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekOutput(
        @BluetoothSeekQualifier.MainView
        viewController: ViewController<Boolean, Unit, BluetoothSeekFailure>
    ): Observable<ViewState<Boolean, Unit, BluetoothSeekFailure>> =
        viewController.output

}