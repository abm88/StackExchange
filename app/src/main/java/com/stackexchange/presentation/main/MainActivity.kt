package com.stackexchange.presentation.main

import androidx.navigation.findNavController
import com.stackexchange.R
import com.stackexchange.base.ui.FullScreenActivity
import com.stackexchange.presentation.stack_exchange.StackExchangeEvent
import com.stackexchange.presentation.stack_exchange.StackExchangeState


class MainActivity : FullScreenActivity<StackExchangeState, StackExchangeEvent, StackExchangeViewModel>() {

    override val contentResourceId: Int
        get() = R.layout.activity_main

    override fun getViewModelClass(): Class<StackExchangeViewModel> = StackExchangeViewModel::class.java

    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
            finish()
        }
    }
}