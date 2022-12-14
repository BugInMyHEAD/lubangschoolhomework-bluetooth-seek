package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.Logger
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class BluetoothSeekUseCase @Inject constructor(
    private val bluetoothSeekRepository: BluetoothSeekRepository,
    @BluetoothSeekQualifier.MainView
    private val mainViewController: ViewController<Boolean, Unit, BluetoothSeekFailure>,
    private val logger: Logger,
) {

    private var disposable: Disposable? = null

    fun start() {
        mainViewController.switchToLoading(Unit)
        try {
            bluetoothSeekRepository.startDiscovery()
            bluetoothSeekRepository.anyPairInRange()
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
        } catch (e: Throwable) {
            handleError(e)
        }
    }

    fun stop() {
        mainViewController.switchToLoading(Unit)
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
            is NoPairedDeviceException -> BluetoothSeekFailure.NO_PAIRED_DEVICES
            else -> BluetoothSeekFailure.UNKNOWN
        })
    }

}