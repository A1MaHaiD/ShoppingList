package com.handroid.shoppinglist.presentation.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.presentation.adapters.ShopListAdapter
import com.handroid.shoppinglist.presentation.ui.fragments.ShopItemFragment
import com.handroid.shoppinglist.presentation.view_models.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishListener {
    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        shopItemContainer = binding.fcvItemContainer
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        val buttonAddItem = binding.fabAddShopItem
        buttonAddItem.setOnClickListener {
            if (switchOrientationOff()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    override fun onEditingFinish() {
        Toast.makeText(this@ShoppingListActivity,"Saved",Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun switchOrientationOff(): Boolean {
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        shopItemContainer?.let {
            with(supportFragmentManager) {
                popBackStack()
                beginTransaction()
                    .replace(it.id, fragment)
                    .addToBackStack(null)
                    .commit()
            }
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
            if (switchOrientationOff()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeSelectedState(it)
        }
    }
}