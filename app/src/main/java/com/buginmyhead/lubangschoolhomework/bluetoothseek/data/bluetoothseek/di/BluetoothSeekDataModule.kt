package com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WeatherInfoModuleBinder {

    // @Binds
    // @Singleton
    // fun bindThreeDayWeatherInfoRepository(
    //     impl: ThreeDayWeatherInfoRepositoryImpl
    // ): ReadOnlyRepository<ThreeDayWeatherInfo>

    // @Binds
    // @Singleton
    // fun bindMainWeatherInfoViewController(
    //     impl: MainWeatherInfoViewControllerImpl
    // ): ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
    //
    // @Binds
    // @Singleton
    // fun bindRefreshWeatherInfoUseCase(
    //     impl: RefreshMainWeatherInfoUseCaseImpl
    // ): RefreshMainWeatherInfoUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object WeatherInfoModuleProvider {

    // @Provides
    // @Singleton
    // fun provideOpenMeteoRemoteDataSource(): OpenMeteo.RemoteDataSource = OpenMeteo.RemoteDataSourceImpl

    // @Provides
    // @Singleton
    // fun provideWeatherInfoOutput(
    //     viewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
    // ): Observable<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> =
    //     viewController.output

}