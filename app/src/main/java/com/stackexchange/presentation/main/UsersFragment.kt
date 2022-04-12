package com.stackexchange.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stackexchange.R
import com.stackexchange.base.ui.ViewModelErrorSuccessFragment
import com.stackexchange.extension.show
import com.stackexchange.presentation.stack_exchange.CallbackParam
import com.stackexchange.presentation.stack_exchange.StackExchangeEvent
import com.stackexchange.presentation.stack_exchange.StackExchangeState
import kotlinx.android.synthetic.main.exchange_stack_screen.*
import kotlinx.coroutines.FlowPreview

class UsersFragment :
    ViewModelErrorSuccessFragment<StackExchangeState, StackExchangeEvent, StackExchangeViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val storiesAdapter by lazy {
        StackExchangeUserAdapter(storyCallback, imageLoader)
    }

    @FlowPreview
    private val storyCallback: (item: CallbackParam) -> Unit = {
        when (it) {
            is CallbackParam.Click -> {
                findNavController().navigate(
                    R.id.action_users_list_navigate_user_detail,
                    Bundle().apply {
                        putParcelable("user_detail", it.data)
                    })
            }
            else -> {
                throw IllegalArgumentException("Unknown callback param: $it")
            }
        }

    }

    override fun getViewModelClass(): Class<StackExchangeViewModel> =
        StackExchangeViewModel::class.java

    override val contentResourceId: Int = R.layout.exchange_stack_screen

    @FlowPreview
    override fun initView() {
        super.initView()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        })
        topStoriesSwipeRefresh.setOnRefreshListener(this)
        topStoriesList.apply {
            adapter = storiesAdapter
            setHasFixedSize(true)
        }
        viewModel.handleEvent(StackExchangeEvent.GetUsers)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
    override fun showLoadingSpinner(loading: Boolean) {
        super.showLoadingSpinner(loading)
        topStoriesProgressBar.show(loading)
    }

    override fun renderState(state: StackExchangeState) {
        super.renderState(state)
        when (state.data) {
            is StackExchangeState.Data.Data -> {
                topStoriesList.show(true)
                topStoriesSwipeRefresh.isRefreshing = false
                storiesAdapter.itemList = state.data.data
                topStoriesEmptyTextView.show(false)
            }
            is StackExchangeState.Data.NoData -> {
                topStoriesSwipeRefresh.isRefreshing = false
                storiesAdapter.itemList.isEmpty().apply {
                    topStoriesList.show(this.not())
                    topStoriesEmptyTextView.show(this)
                }
            }
            else -> {
                // do nothing
            }
        }
    }

    @FlowPreview
    override fun onRefresh() {
        topStoriesSwipeRefresh.isRefreshing = false
        viewModel.handleEvent(StackExchangeEvent.GetUsers)
    }

}