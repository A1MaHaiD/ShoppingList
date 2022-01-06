package com.handroid.shoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH

class ShopListAdapter : RecyclerView.Adapter<ShopItemVH>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_unselected,
            parent,
            false
        )
        return ShopItemVH(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemVH, position: Int) {
        val shopItem = shopList[position]
        val statusSelected = if (shopItem.isSelected) {
            "Active"
        } else {
            "Inactive"
        }
        with(viewHolder) {
            itemView.setOnLongClickListener {
                true
            }
            if (shopItem.isSelected) {
                tvName.text = "${shopItem.name} $statusSelected"
                tvCount.text = shopItem.count.toString()
                tvName.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.holo_red_light
                    )
                )
            }
        }
    }

    override fun onViewRecycled(viewHolder: ShopItemVH) {
        super.onViewRecycled(viewHolder)
        with(viewHolder){
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

/*        override fun getItemViewType(position: Int): Int {
        return position
    }*/

    override fun getItemCount(): Int {
        return shopList.size
    }
}