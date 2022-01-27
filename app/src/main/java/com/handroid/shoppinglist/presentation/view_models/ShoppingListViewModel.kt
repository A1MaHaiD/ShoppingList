package com.handroid.shoppinglist.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handroid.shoppinglist.domain.EditShopItemUseCase
import com.handroid.shoppinglist.domain.GetShopListUseCase
import com.handroid.shoppinglist.domain.RemoveShopItemUseCase
import com.handroid.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingListViewModel @Inject constructor(
    private val getShopListUserCase: GetShopListUseCase,
    private val removeShopItemUserCase: RemoveShopItemUseCase,
    private val editShopItemUserCase: EditShopItemUseCase
) : ViewModel() {

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