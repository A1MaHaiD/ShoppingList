package com.handroid.shoppinglist.domain.item

import com.handroid.shoppinglist.domain.ShopItem

class AddShopElementUseCase(private val shopItemRepository: ShopItemRepository) {

    fun addShopElement(shopItem: ShopItem){
        shopItemRepository.addShopElement(shopItem)
    }
}