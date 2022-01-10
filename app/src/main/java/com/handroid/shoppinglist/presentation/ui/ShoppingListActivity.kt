package com.handroid.shoppinglist.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.presentation.adapters.ShopListAdapter
import com.handroid.shoppinglist.presentation.view_models.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        val buttonAddItem = binding.fabAddShopItem
        buttonAddItem.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
        val view = binding.root
        setContentView(view)
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
        setupOnClickListener()
        setupLongClickListener()
        setupSwipeListener()
    }

    private fun setupSwipeListener() {
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
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)
    }

    private fun setupOnClickListener() {
        shopListAdapter.onShopItemClickListener = {
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeSelectedState(it)
        }
    }
}