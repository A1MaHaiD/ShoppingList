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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.i("ShoppingListActivity","element $it")
        }
        viewModel.getShopList()
    }
}