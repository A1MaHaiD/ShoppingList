package com.handroid.shoppinglist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.handroid.shoppinglist.databinding.ActivityShopItemBinding

lateinit var binding: ActivityShopItemBinding

class ShopItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}