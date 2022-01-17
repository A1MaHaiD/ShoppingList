package com.handroid.shoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.databinding.ItemShopSelectedBinding
import com.handroid.shoppinglist.databinding.ItemShopUnselectedBinding
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH
import com.handroid.shoppinglist.presentation.utils.ShopItemDiffCallback

class ShopListAdapter : ListAdapter<ShopItem, ShopItemVH>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        val layout = when (viewType) {
            IS_SELECTED -> R.layout.item_shop_selected
            NOT_SELECTED -> R.layout.item_shop_unselected
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent, false
        )
        return ShopItemVH(binding)
    }

    override fun onBindViewHolder(viewHolder: ShopItemVH, position: Int) {
        val shopItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopUnselectedBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemShopSelectedBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isSelected) {
            IS_SELECTED
        } else {
            NOT_SELECTED
        }
    }

    companion object {
        const val IS_SELECTED = 100
        const val NOT_SELECTED = 101
        const val MAX_POOL_SIZE = 12
    }
}
