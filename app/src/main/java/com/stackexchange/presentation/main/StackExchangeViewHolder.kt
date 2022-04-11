package com.stackexchange.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stackexchange.R
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.presentation.stack_exchange.CallbackParam
import com.stackexchange.util.ImageLoader
import kotlinx.android.synthetic.main.item_user_grid.view.*

class StackExchangeViewHolder constructor(
    containerView: View,
    private val callback: (entity: CallbackParam) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(
            parent: ViewGroup,
            callback: (entity: CallbackParam) -> Unit,
            imageLoader: ImageLoader
        ) = StackExchangeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_grid, parent, false), callback, imageLoader
        )
    }

    fun bind(story: StackExchangeUserEntity) {
        itemView.setOnClickListener {
            callback(CallbackParam.Click(story))
        }
        itemView.setOnClickListener {
            callback(CallbackParam.Click(story))
        }

        itemView.itemStoryTitle.text = story.userName
        itemView.itemStoryDateTime.text = story.createDate
        imageLoader.loadImage(
            itemView.itemStoryImage,
            0,
            0,
            story.avatar
        )
    }
}