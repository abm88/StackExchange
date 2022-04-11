package com.stackexchange.presentation.main

import com.stackexchange.base.StackExchangeParam
import com.stackexchange.base.domain.GeneralUseCase
import com.stackexchange.base.loading
import com.stackexchange.base.viewmodel.BaseFlowViewModel
import com.stackexchange.di.scope.MainDispatcher
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.presentation.stack_exchange.StackExchangeEvent
import com.stackexchange.presentation.stack_exchange.StackExchangeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import java.lang.IllegalArgumentException
import javax.inject.Inject


class StackExchangeViewModel @Inject constructor(
    private val stackExchangeUsersUseCase: GeneralUseCase<StackExchangeParam, List<StackExchangeUserEntity>>,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : BaseFlowViewModel<StackExchangeState, StackExchangeEvent>() {

    override val initialState: StackExchangeState
        get() = StackExchangeState()
    override val loadingState: StackExchangeState
        get() = initialState.loading()

    override fun handleEvent(event: StackExchangeEvent) {
        when (event) {
            is StackExchangeEvent.SearchUsers -> {
                searchStackExchangeUsers(event.data)
            }
            StackExchangeEvent.GetUsers -> {
                getStackExchangeUsers()
            }
            else -> {
                throw IllegalArgumentException("Unknown event: $event")
            }
        }
    }

    private fun searchStackExchangeUsers(query: String) = triggerActionStartWithLoading(dispatcher) {
        stackExchangeUsersUseCase.execute(StackExchangeParam.SearchUsers(query)).collect { result ->
            result.subscribe(
                successAction = {
                    initialState.copy(
                        data = StackExchangeState.Data.Data(it.extractData()),
                        baseState = initialState.baseState.noErrorNoLoading()
                    )
                },
                failureAction = {
                    initialState.copy(
                        data = StackExchangeState.Data.NoData,
                        baseState = initialState.baseState.onErrorNoLoading(it.extractError())
                    )
                }
            )
        }
    }

    private fun getStackExchangeUsers() = triggerActionStartWithLoading(dispatcher) {
        stackExchangeUsersUseCase.execute(StackExchangeParam.GetUsers).collect { result ->
            result.subscribe(
                successAction = {
                    initialState.copy(
                        data = StackExchangeState.Data.Data(it.extractData()),
                        baseState = initialState.baseState.noErrorNoLoading()
                    )
                },
                failureAction = {
                    initialState.copy(
                        data = StackExchangeState.Data.NoData,
                        baseState = initialState.baseState.onErrorNoLoading(it.extractError())
                    )
                }
            )
        }
    }
}