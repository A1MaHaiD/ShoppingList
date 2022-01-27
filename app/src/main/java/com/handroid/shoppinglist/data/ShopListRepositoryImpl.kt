package com.handroid.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.handroid.shoppinglist.data.database.ShopListDao
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao: ShopListDao,
    private val mapper: ShopListMapper
) : ShopListRepository {


    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItemData(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun removeShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItemData(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItemData(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItemData(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopListData()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}
/**
 * @using
 * @MediatorLiveData with method addSource()
 * override fun getShopList(): LiveData<List<ShopItem>> =
MediatorLiveData<List<ShopItem>>().apply {
addSource(shopListDao.getShopListData()) {
value = mapper.mapListDbModelToListEntity(it)
}
}*/