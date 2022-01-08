package com.handroid.shoppinglist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH
import com.handroid.shoppinglist.presentation.utils.ShopItemDiffCallback
import com.handroid.shoppinglist.presentation.utils.ShopListDiffCallback
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem, ShopItemVH>(ShopItemDiffCallback()) {

    var count = 0

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        val layout = when (viewType) {
            IS_SELECTED -> R.layout.item_shop_selected
            NOT_SELECTED -> R.layout.item_shop_unselected
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemVH(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemVH, position: Int) {
        Log.i("ShopListAdapter", "onBindViewHolder: ${++count}")

        val shopItem = getItem(position)
        with(viewHolder) {
                view.setOnLongClickListener {
                    onShopItemLongClickListener?.invoke(shopItem)
                    notifyItemChanged(position)
                    true
                }
                view.setOnClickListener {
                    onShopItemClickListener?.invoke(shopItem)
                }

            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    override fun onViewRecycled(viewHolder: ShopItemVH) {
        super.onViewRecycled(viewHolder)
        with(viewHolder) {
            tvName.text = ""
            tvCount.text = ""
            tvName.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    android.R.color.white
                )
            )
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
