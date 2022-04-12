package com.stackexchange.base.viewmodel

import androidx.lifecycle.ViewModel
import com.stackexchange.base.BaseState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<STATE : BaseState>(
    val initialState: STATE
) : ViewModel() {

    abstract val state: SingleLiveEvent<STATE>

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun add(subject: Disposable) {
        compositeDisposable.add(subject)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}