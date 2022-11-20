package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


@Module
@InstallIn(ActivityRetainedComponent::class)
object MainUiModuleProvider {

    @Provides
    @ActivityRetainedScoped
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekViewController(
    ): ViewController<Boolean, Unit, BluetoothSeekFailure> = object : AbstractViewController<Boolean, Unit, BluetoothSeekFailure>() {

        override val output = PublishSubject.create<ViewState<Boolean, Unit, BluetoothSeekFailure>>()

    }

    @Provides
    @ActivityRetainedScoped
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekOutput(
        @BluetoothSeekQualifier.MainView
        viewController: ViewController<Boolean, Unit, BluetoothSeekFailure>
    ): Observable<ViewState<Boolean, Unit, BluetoothSeekFailure>> =
        viewController.output

}