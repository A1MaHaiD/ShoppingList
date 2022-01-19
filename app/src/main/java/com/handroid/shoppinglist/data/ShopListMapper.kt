package com.handroid.shoppinglist.data

import com.handroid.shoppinglist.data.database.ShopItemDbModel
import com.handroid.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        count = shopItem.count,
        name = shopItem.toString(),
        isSelected = shopItem.isSelected
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        count = shopItemDbModel.count,
        name = shopItemDbModel.toString(),
        isSelected = shopItemDbModel.isSelected
    )

    fun mapListDbModelToListEntity(shopListDbModel: List<ShopItemDbModel>) = shopListDbModel.map {
        mapDbModelToEntity(it)
    }
}