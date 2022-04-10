package com.stackexchange.extension

import com.stackexchange.base.ResultResponse
import kotlinx.coroutines.flow.*


fun <T> Flow<ResultResponse<T>>.handleSuccessFailure(
    doOnError: (e: Throwable) -> Unit,
    onErrorEmit: (e: Throwable) -> ResultResponse<T>
) = this.catch { e ->
    doOnError(e)
    emit(onErrorEmit(e))
}
