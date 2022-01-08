package com.handroid.shoppinglist.domain.list

import androidx.lifecycle.LiveData
import com.handroid.shoppinglist.domain.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}