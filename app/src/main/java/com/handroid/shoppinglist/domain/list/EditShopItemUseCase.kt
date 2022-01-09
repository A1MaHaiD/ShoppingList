package com.handroid.shoppinglist.domain.list

import com.handroid.shoppinglist.domain.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}