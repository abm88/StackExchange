package com.stackexchange.base.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.stackexchange.base.BaseEvent
import com.stackexchange.base.ViewModelState
import com.stackexchange.base.viewmodel.BaseFlowViewModel

abstract class FullScreenActivity<STATE : ViewModelState, EVENT : BaseEvent, VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> :
    BaseActivity<STATE, EVENT, VIEWMODEL>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.apply {
            hide()
        }
        super.onCreate(savedInstanceState)
    }


}