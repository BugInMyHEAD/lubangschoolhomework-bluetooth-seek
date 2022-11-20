package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.di

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewTargetQualifier
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BluetoothSeekModuleBinder {

    // @Binds
    // @Singleton
    // fun bindThreeDayWeatherInfoRepository(
    //     impl: ThreeDayWeatherInfoRepositoryImpl
    // ): ReadOnlyRepository<ThreeDayWeatherInfo>

    // @Binds
    // @Singleton
    // fun bindRefreshWeatherInfoUseCase(
    //     impl: RefreshMainWeatherInfoUseCaseImpl
    // ): RefreshMainWeatherInfoUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object BluetoothSeekModuleProvider {

    @Provides
    @Singleton
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekViewController(
    ): ViewController<Boolean, Unit, BluetoothSeekFailure> = object : AbstractViewController<Boolean, Unit, BluetoothSeekFailure>() {

        override val output = PublishSubject.create<ViewState<Boolean, Unit, BluetoothSeekFailure>>()

    }

    @Provides
    @Singleton
    @BluetoothSeekQualifier.MainView
    fun provideBluetoothSeekOutput(
        @BluetoothSeekQualifier.MainView
        viewController: ViewController<Boolean, Unit, BluetoothSeekFailure>
    ): Observable<ViewState<Boolean, Unit, BluetoothSeekFailure>> =
        viewController.output

}

annotation class BluetoothSeekQualifier {

    @ViewTargetQualifier
    annotation class MainView

}