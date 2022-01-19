package com.handroid.shoppinglist.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.domain.EditShopItemUseCase
import com.handroid.shoppinglist.domain.GetShopListUseCase
import com.handroid.shoppinglist.domain.RemoveShopItemUseCase
import com.handroid.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUserCase = GetShopListUseCase(repository)
    private val removeShopItemUserCase = RemoveShopItemUseCase(repository)
    private val editShopItemUserCase = EditShopItemUseCase(repository)

    val shopList = getShopListUserCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeShopItemUserCase.removeShopItem(shopItem)
        }
    }

    fun changeSelectedState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(isSelected = !shopItem.isSelected)
            editShopItemUserCase.editShopItem(newItem)
        }
    }
}