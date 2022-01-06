package com.handroid.shoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.viewholders.ShopItemVH

class ShopListAdapter : RecyclerView.Adapter<ShopItemVH>() {

    private val list = listOf<ShopItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_selected, parent, false)
        return ShopItemVH(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemVH, position: Int) {
        val shopItem = list[position]
        with(viewHolder){
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}