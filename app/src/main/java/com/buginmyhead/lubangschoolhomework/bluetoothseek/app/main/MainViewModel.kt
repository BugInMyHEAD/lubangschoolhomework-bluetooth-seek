package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buginmyhead.lubangschoolhomework.bluetoothseek.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.bluetoothseek.data.bluetoothseek.di.BluetoothSeekQualifier
import com.buginmyhead.lubangschoolhomework.bluetoothseek.domain.bluetoothseek.BluetoothSeekFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @BluetoothSeekQualifier.MainView
    bluetoothSeekOutput: Observable<ViewState<Boolean, Unit, BluetoothSeekFailure>>
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bluetoothSeekLiveData = MutableLiveData<ViewState<Boolean, Unit, BluetoothSeekFailure>>()
    val bluetoothSeekLiveData: LiveData<ViewState<Boolean, Unit, BluetoothSeekFailure>> get() = _bluetoothSeekLiveData

    init {
        bluetoothSeekOutput.subscribe(_bluetoothSeekLiveData::postValue).also(compositeDisposable::add)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}