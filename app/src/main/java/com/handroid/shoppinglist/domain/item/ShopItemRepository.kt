package com.handroid.shoppinglist.domain.item

import com.handroid.shoppinglist.domain.ShopItem

interface ShopItemRepository {

    fun addShopElement(shopItem: ShopItem)

    fun editShopElement(shopItem: ShopItem)

    fun getShopElement(shopItemId:Int) : ShopItem

    fun saveShopElement(shopItem: ShopItem)
}