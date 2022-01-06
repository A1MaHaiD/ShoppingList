package com.handroid.shoppinglist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopItemVH>() {

    var count = 0
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        Log.i("ShopListAdapter", "onCreatedViewHolder: ${++count}")
        val layout = when (viewType){
             IS_SELECTED -> LayoutInflater.from(parent.context).inflate(
                    R.layout.item_shop_selected,
                    parent,
                    false
                )
            NOT_SELECTED -> LayoutInflater.from(parent.context).inflate(
                R.layout.item_shop_unselected,
                parent,
                false
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ShopItemVH(layout)
    }

    override fun onBindViewHolder(viewHolder: ShopItemVH, position: Int) {
        val shopItem = shopList[position]
        with(viewHolder) {
            itemView.setOnLongClickListener {
                true
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
    }
}