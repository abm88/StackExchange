package com.stackexchange

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

class HistoryObserver<T> : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)
    private val history: MutableList<T> = mutableListOf()


    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        history.clear()
        history.add(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun getHistoryItem() = if (history.isNotEmpty()) {
        history[0]
    } else {
        throw IllegalArgumentException("History is empty")
    }

    fun assertNotEmpty() : HistoryObserver<T> {
        if (history.isEmpty()){
            throw IllegalStateException()
        }
        return this
    }

}