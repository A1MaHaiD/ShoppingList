package com.handroid.shoppinglist.domain.list

import androidx.lifecycle.LiveData
import com.handroid.shoppinglist.domain.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}