package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BluetoothSeekUseCase @Inject constructor(
    private val bluetoothSeekRepository: BluetoothSeekRepository,
    @BluetoothSeekQualifier.MainView
    private val mainViewController: ViewController<Boolean, Unit, BluetoothSeekFailure>,
    private val logger: Logger,
) {

    private var disposable: Disposable? = null

    fun start() {
        try {
            bluetoothSeekRepository.startDiscovery()
        } catch (e: Throwable) {
            handleError(e)
        }
        Observable.interval(0L, 20L, TimeUnit.SECONDS)
            .switchMap { bluetoothSeekRepository.anyPairInRange() }
            .subscribe(object : Observer<Boolean> {

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Boolean) {
                    mainViewController.switchToSuccess(t)
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    handleError(e)
                }

            })
    }

    fun stop() {
        disposable?.dispose()
        disposable = null
        bluetoothSeekRepository.stopDiscovery()
    }

    private fun handleError(e: Throwable) {
        logger.errorFromRepository(bluetoothSeekRepository, e)
        mainViewController.switchToFailure(when (e) {
            is NoBluetoothSupportException -> BluetoothSeekFailure.UNSUPPORTED
            is BluetoothPermissionException -> BluetoothSeekFailure.NO_PERMISSION
            is BluetoothRadioOffException -> BluetoothSeekFailure.RADIO_OFF
            else -> BluetoothSeekFailure.UNKNOWN
        })
    }

}