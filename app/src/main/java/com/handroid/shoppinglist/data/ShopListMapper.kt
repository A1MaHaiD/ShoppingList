package com.handroid.shoppinglist.data

import com.handroid.shoppinglist.data.database.ShopItemDbModel
import com.handroid.shoppinglist.domain.ShopItem
import javax.inject.Inject

class ShopListMapper @Inject constructor() {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        isSelected = shopItem.isSelected
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        isSelected = shopItemDbModel.isSelected
    )

    fun mapListDbModelToListEntity(shopListDbModel: List<ShopItemDbModel>) = shopListDbModel.map {
        mapDbModelToEntity(it)
    }
}