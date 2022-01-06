package com.handroid.shoppinglist.presentation.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ItemShopUnselectedBinding

class ShopItemVH(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemShopUnselectedBinding.bind(view)
    val tvName = binding.tvName
    val tvCount = binding.tvCount
}