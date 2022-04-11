package com.stackexchange.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.presentation.stack_exchange.CallbackParam
import com.stackexchange.util.ImageLoader

class StackExchangeUserAdapter(
    private val callback: (entity: CallbackParam) -> Unit,
    private val imageLoader: ImageLoader,
) : RecyclerView.Adapter<StackExchangeViewHolder>() {

    var itemList: List<StackExchangeUserEntity> = emptyList()
        set(value) {
            if (value.isEmpty().not()){
                field = value
                notifyDataSetChanged()
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackExchangeViewHolder =
        StackExchangeViewHolder.create(parent, callback, imageLoader)

    override fun onBindViewHolder(holder: StackExchangeViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int  = itemList.size


}