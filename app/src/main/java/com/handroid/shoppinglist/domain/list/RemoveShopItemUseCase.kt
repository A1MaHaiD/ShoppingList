package com.handroid.shoppinglist.domain.list

import com.handroid.shoppinglist.domain.ShopItem

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun removeShopItem(shopItem: ShopItem){
        shopListRepository.removeShopItem(shopItem)
    }
}