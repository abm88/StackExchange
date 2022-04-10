package com.stackexchange.error

interface ErrorContainer {
    fun getError(throwable: Throwable): ErrorEntity
}
