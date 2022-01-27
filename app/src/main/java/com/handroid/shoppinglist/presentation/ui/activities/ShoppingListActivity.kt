package com.handroid.shoppinglist.presentation.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.presentation.ShoppingApp
import com.handroid.shoppinglist.presentation.adapters.ShopListAdapter
import com.handroid.shoppinglist.presentation.ui.fragments.ShopItemFragment
import com.handroid.shoppinglist.presentation.view_models.ShoppingListViewModel
import com.handroid.shoppinglist.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class ShoppingListActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishListener {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ShoppingApp).component
            .activityComponentFactory()
            .create(1,"Activity",10,true)
    }

    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[ShoppingListViewModel::class.java]
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
        Toast.makeText(this@ShoppingListActivity, "Saved", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun switchOrientationOff(): Boolean {
        return binding.fcvItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        binding.fcvItemContainer?.let {
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