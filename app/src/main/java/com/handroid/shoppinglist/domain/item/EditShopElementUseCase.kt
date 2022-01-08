package com.handroid.shoppinglist.domain.item

import com.handroid.shoppinglist.domain.ShopItem

class EditShopElementUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editShopElement(shopItem: ShopItem){
        shopItemRepository.editShopElement(shopItem)
    }
}