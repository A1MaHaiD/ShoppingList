package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.presentation.adapters.ShopListAdapter
import com.handroid.shoppinglist.presentation.viewmodel.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvShopList
        with(recyclerView) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            with(recycledViewPool) {
                setMaxRecycledViews(
                    ShopListAdapter.IS_SELECTED,
                    ShopListAdapter.MAX_POOL_SIZE
                )
                setMaxRecycledViews(
                    ShopListAdapter.NOT_SELECTED,
                    ShopListAdapter.MAX_POOL_SIZE
                )
            }
        }
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeSelectedState(it)
        }
    }
}