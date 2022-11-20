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

    operator fun invoke() {
        Observable.interval(0L, 5L, TimeUnit.SECONDS)
            .switchMapSingle { bluetoothSeekRepository.isAnyPairInRange() }
            .subscribe(object : Observer<Boolean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Boolean) {
                    mainViewController.switchToSuccess(t)
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    logger.errorFromRepository(bluetoothSeekRepository, e)
                    mainViewController.switchToFailure(when (e) {
                        is BluetoothPermissionException -> BluetoothSeekFailure.NO_PERMISSION
                        is BluetoothRadioOffException -> BluetoothSeekFailure.RADIO_OFF
                        else -> BluetoothSeekFailure.UNKNOWN
                    })
                }

            })
    }

}