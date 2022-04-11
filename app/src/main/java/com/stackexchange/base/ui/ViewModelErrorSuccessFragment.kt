package com.stackexchange.base.ui


import com.stackexchange.base.BaseEvent
import com.stackexchange.base.ViewModelState
import com.stackexchange.base.viewmodel.BaseFlowViewModel
import com.stackexchange.util.ImageLoader
import javax.inject.Inject


abstract class ViewModelErrorSuccessFragment<STATE : ViewModelState, EVENT : BaseEvent,
        VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> :
    BaseViewModelFragment<STATE, EVENT, VIEWMODEL>() {


    @Inject
    lateinit var imageLoader: ImageLoader


    override fun renderState(state: STATE) {
        super.renderState(state)
        showLoadingSpinner(state.baseState.loading)
    }

    open fun showLoadingSpinner(loading: Boolean) {
        // do nothing
    }


}