package com.stackexchange.extension


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LifecycleOwner.observeLiveData(
    liveData: LiveData<T>,
    crossinline onChanged: (data: T) -> Unit
) {
    liveData.observe(this) {
        it?.let {
            onChanged(it)
        }
    }
}
