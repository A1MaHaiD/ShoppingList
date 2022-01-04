package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.handroid.shoppinglist.databinding.ActivityShoppingListBinding
import com.handroid.shoppinglist.presentation.viewmodel.ShoppingListViewModel

class ShoppingListActivity : AppCompatActivity() {

    lateinit var binding: ActivityShoppingListBinding
    private lateinit var viewModel: ShoppingListViewModel
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this) {
            Log.i("ShoppingListActivity", "element $it")
            if (count == 0) {
                count++
                val item = it[0]
                viewModel.changeSelectedState(item)
                Log.i("ChangeTest", "change element $it")
            }
        }
    }
}