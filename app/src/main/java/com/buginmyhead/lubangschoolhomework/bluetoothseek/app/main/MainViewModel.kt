package com.buginmyhead.lubangschoolhomework.bluetoothseek.app.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}