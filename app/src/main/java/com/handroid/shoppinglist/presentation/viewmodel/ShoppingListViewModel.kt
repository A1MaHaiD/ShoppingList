package com.handroid.shoppinglist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.data.ShopListRepositoryImpl.getShopItem
import com.handroid.shoppinglist.domain.*

class ShoppingListViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUserCase = GetShopListUseCase(repository)
    private val removeShopItemUserCase = RemoveShopItemUseCase(repository)
    private val editShopItemUserCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUserCase.getShopList()
        shopList.value = list
    }

    fun changeSelectedState(shopItem:ShopItem){
        val newItem = shopItem.copy(isSelected = !shopItem.isSelected)
        editShopItemUserCase.editShopItem(newItem)
        getShopList()
    }

    fun removeShopItem(shopItem:ShopItem){
        removeShopItemUserCase.removeShopItem(shopItem)
        getShopList()
    }


}