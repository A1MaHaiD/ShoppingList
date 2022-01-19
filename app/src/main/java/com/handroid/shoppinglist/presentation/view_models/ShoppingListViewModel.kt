package com.handroid.shoppinglist.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.domain.EditShopItemUseCase
import com.handroid.shoppinglist.domain.GetShopListUseCase
import com.handroid.shoppinglist.domain.RemoveShopItemUseCase
import com.handroid.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUserCase = GetShopListUseCase(repository)
    private val removeShopItemUserCase = RemoveShopItemUseCase(repository)
    private val editShopItemUserCase = EditShopItemUseCase(repository)

    val shopList = getShopListUserCase.getShopList()

    private val scope = CoroutineScope(Dispatchers.IO)

    fun removeShopItem(shopItem: ShopItem) {
        scope.launch {
            removeShopItemUserCase.removeShopItem(shopItem)
        }
    }

    fun changeSelectedState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(isSelected = !shopItem.isSelected)
            editShopItemUserCase.editShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}