package com.stackexchange.presentation.stack_exchange

import com.stackexchange.base.BaseEvent
import com.stackexchange.base.BaseState
import com.stackexchange.base.ViewModelState
import com.stackexchange.domain.model.StackExchangeUserEntity

sealed class CallbackParam {
    data class Click(val data: StackExchangeUserEntity) : CallbackParam()
}

sealed class StackExchangeEvent : BaseEvent {
    object GetUsers : StackExchangeEvent()
    data class SearchUsers(val data: String = ""): StackExchangeEvent()
}

data class StackExchangeState(
    val data: Data = Data.NoData,
    override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class Data(val data: List<StackExchangeUserEntity>) : StackExchangeState.Data()
        object NoData : StackExchangeState.Data()
    }
}
