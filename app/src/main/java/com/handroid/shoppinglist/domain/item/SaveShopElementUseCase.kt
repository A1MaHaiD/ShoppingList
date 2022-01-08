package com.handroid.shoppinglist.domain.item

import com.handroid.shoppinglist.domain.ShopItem

class SaveShopElementUseCase(private val shopItemRepository: ShopItemRepository) {
    fun saveShopElement(shopItem: ShopItem){
        shopItemRepository.saveShopElement(shopItem)
    }
}