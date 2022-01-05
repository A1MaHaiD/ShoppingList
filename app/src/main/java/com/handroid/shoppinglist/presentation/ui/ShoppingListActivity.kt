package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.handroid.shoppinglist.R
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.viewmodel.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
//    private lateinit var llShopList:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        binding.llShopList

        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            showList(it)
        }
    }

    fun showList(list: List<ShopItem>){
        for(shopItem in list){
            val layoutId = if (shopItem.isSelected){
                R.layout.item_shop_selected
            } else {
                R.layout.item_shop_unselected
            }
            val view = LayoutInflater.from(this).inflate(layoutId,binding.llShopList, false)
            binding.llShopList.addView(view)
        }
    }
}