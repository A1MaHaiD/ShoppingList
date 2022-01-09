package com.handroid.shoppinglist.presentation.view_models

import androidx.lifecycle.ViewModel
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.domain.*
import com.handroid.shoppinglist.domain.list.EditShopItemUseCase
import com.handroid.shoppinglist.domain.list.GetShopListUseCase
import com.handroid.shoppinglist.domain.list.RemoveShopItemUseCase

class ShoppingListViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUserCase = GetShopListUseCase(repository)
    private val removeShopItemUserCase = RemoveShopItemUseCase(repository)
    private val editShopItemUserCase = EditShopItemUseCase(repository)

    val shopList = getShopListUserCase.getShopList()

    fun removeShopItem(shopItem:ShopItem){
        removeShopItemUserCase.removeShopItem(shopItem)
    }

    fun changeSelectedState(shopItem:ShopItem){
        val newItem = shopItem.copy(isSelected = !shopItem.isSelected)
        editShopItemUserCase.editShopItem(newItem)
    }
}