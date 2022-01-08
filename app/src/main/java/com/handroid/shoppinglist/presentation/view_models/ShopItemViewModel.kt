package com.handroid.shoppinglist.presentation.view_models

import androidx.lifecycle.ViewModel
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.domain.list.EditShopItemUseCase
import com.handroid.shoppinglist.domain.list.GetShopListUseCase

class ShopItemViewModel:ViewModel() {

    private val  repository = ShopListRepositoryImpl

    private val getShopElementUseCase = GetShopListUseCase(repository)
    private val editShopElementUseCase = EditShopItemUseCase(repository)
    private val saveShopElementUseCase = EditShopItemUseCase(repository)

    val shopList = getShopElementUseCase.getShopList()

    fun editShopItem(shopItem:ShopItem){
        editShopElementUseCase.editShopItem(shopItem)
    }

    fun  saveShopItem(shopItem: ShopItem){
        saveShopElementUseCase.editShopItem(shopItem)
    }
}