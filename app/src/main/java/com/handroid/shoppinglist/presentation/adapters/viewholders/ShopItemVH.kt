package com.handroid.shoppinglist.presentation.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ItemShopSelectedBinding

class ShopItemVH(val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemShopSelectedBinding.bind(view)
    val tvName = binding.tvName
    val tvCount = binding.tvCount
}