package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.handroid.shoppinglist.databinding.ActivityShopItemBinding
import com.handroid.shoppinglist.presentation.view_models.ShopItemViewModel

lateinit var binding: ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {

    lateinit var viewModel: ShopItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}