package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.viewmodel.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var linearLayoutShopList:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        linearLayoutShopList = binding.llShopList
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    fun showList(list: List<ShopItem>){
        linearLayoutShopList.removeAllViews()
        for(shopItem in list){
            val layoutId = if (shopItem.isSelected){
                R.layout.item_shop_selected
            } else {
                R.layout.item_shop_unselected
            }
            val view = LayoutInflater.from(this).inflate(layoutId,linearLayoutShopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeSelectedState(shopItem)
                true
            }
            linearLayoutShopList.addView(view)
        }
    }
}