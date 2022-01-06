package com.handroid.shoppinglist.presentation.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.R

class ShopItemVH(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById(R.id.tv_name) as TextView
    val tvCount = view.findViewById(R.id.tv_count) as TextView
}