package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
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
            shopListAdapter.submitList(it)
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
        with(shopListAdapter) {
            setupLongClickListener()
            setupOnClickListener()
            setupSwipeListener()
        }

    }

    private fun ShopListAdapter.setupSwipeListener() {
        val callBack = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)
    }

    private fun ShopListAdapter.setupOnClickListener() {
        onShopItemClickListener = {
            Log.i(
                "onShopItemClickListener",
                "item: ${it.id}, ${it.name}, ${it.count}"
            )
        }
    }

    private fun ShopListAdapter.setupLongClickListener() {
        onShopItemLongClickListener = {
            viewModel.changeSelectedState(it)
        }
    }
}