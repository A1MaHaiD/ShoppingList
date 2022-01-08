package com.handroid.shoppinglist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH
import com.handroid.shoppinglist.presentation.utils.ShopListDiffCallback
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopItemVH>() {

    var count = 0
    var shopList = listOf<ShopItem>()
        set(value) {
            val callBackDiff = ShopListDiffCallback(shopList,value)
            val diffResult = DiffUtil.calculateDiff(callBackDiff)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }
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

        val shopItem = shopList[position]
        with(viewHolder) {
            with(itemView) {
                setOnLongClickListener {
                    onShopItemLongClickListener?.invoke(shopItem)
                    true
                }
                setOnClickListener {
                    onShopItemClickListener?.invoke(shopItem)
                }
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
                    itemView.context,
                    android.R.color.white
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.isSelected) {
            IS_SELECTED
        } else {
            NOT_SELECTED
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    companion object {
        const val IS_SELECTED = 100
        const val NOT_SELECTED = 101
        const val MAX_POOL_SIZE = 12
    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }
}
