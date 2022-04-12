package com.stackexchange.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.stackexchange.R
import com.stackexchange.base.ui.ViewModelErrorSuccessFragment
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.presentation.main.StackExchangeViewModel
import com.stackexchange.presentation.stack_exchange.StackExchangeEvent
import com.stackexchange.presentation.stack_exchange.StackExchangeState
import kotlinx.android.synthetic.main.detailt_screen.*


class DetailFragment: ViewModelErrorSuccessFragment<StackExchangeState, StackExchangeEvent, StackExchangeViewModel>(){
    override val contentResourceId: Int = R.layout.detailt_screen

    override fun getViewModelClass(): Class<StackExchangeViewModel> = StackExchangeViewModel::class.java

    override fun initView() {
        arguments?.getParcelable<StackExchangeUserEntity>("user_detail")?.apply {
            detailTitleTextView.text = userName
            detailReputationTextView.text = getString(R.string.reputation, reputation)
            if(topTags.isNullOrEmpty()){
                detailTagTextView.text = getString(R.string.no_tags)
            }else {
                detailTagTextView.text = getString(R.string.tags, topTags)
            }
            detailLinkTextView.text = link
            detailLinkTextView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }
            imageLoader.loadImage(
                detailImageView, 0, 0, avatar)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}