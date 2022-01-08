package com.handroid.shoppinglist.domain.list

import com.handroid.shoppinglist.domain.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}