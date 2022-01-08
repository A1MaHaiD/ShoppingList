package com.handroid.shoppinglist.presentation.view_models

import androidx.lifecycle.ViewModel
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.domain.list.AddShopItemUseCase
import com.handroid.shoppinglist.domain.list.EditShopItemUseCase
import com.handroid.shoppinglist.domain.list.GetShopItemUseCase

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopElementUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopElementUseCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopElementUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        addShopItemUseCase.addShopItem(name, count)
    }

    fun editShopItem(shopItem: ShopItem) {
        editShopElementUseCase.editShopItem(shopItem)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }
}