package com.handroid.shoppinglist.domain.item

import com.handroid.shoppinglist.domain.ShopItem

class GetShopElementUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopElement(shopItemId:Int):ShopItem{
        return shopItemRepository.getShopElement(shopItemId)
    }
}