package com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek

import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.bluetoothseek.logging.Logger
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class BluetoothSeekUseCase @Inject constructor(
    private val bluetoothSeekRepository: BluetoothSeekRepository,
    @BluetoothSeekQualifier.MainView
    private val mainViewController: ViewController<Boolean, Unit, BluetoothSeekFailure>,
    private val logger: Logger,
) {

    operator fun invoke() {
        bluetoothSeekRepository.isAnyPairInRange()
            .subscribe(object : SingleObserver<Boolean> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: Boolean) {
                    mainViewController.switchToSuccess(t)
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