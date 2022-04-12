package com.stackexchange.presentation.main

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.stackexchange.R
import com.stackexchange.base.ui.BaseActivity
import com.stackexchange.base.ui.FullScreenActivity
import com.stackexchange.presentation.stack_exchange.StackExchangeEvent
import com.stackexchange.presentation.stack_exchange.StackExchangeState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.exchange_stack_screen.*


class MainActivity : BaseActivity<StackExchangeState, StackExchangeEvent, StackExchangeViewModel>(),
    SearchView.OnQueryTextListener {

    override val contentResourceId: Int
        get() = R.layout.activity_main

    override fun getViewModelClass(): Class<StackExchangeViewModel> =
        StackExchangeViewModel::class.java

    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isEmpty()) {
            viewModel.handleEvent(StackExchangeEvent.GetUsers)
        } else {
            viewModel.handleEvent(StackExchangeEvent.SearchUsers(query))
        }
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.isEmpty()) {
            viewModel.handleEvent(StackExchangeEvent.GetUsers)
        }
        return false
    }
}