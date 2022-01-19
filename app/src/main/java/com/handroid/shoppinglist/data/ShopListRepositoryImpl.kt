package com.handroid.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.handroid.shoppinglist.data.database.AppDatabase
import com.handroid.shoppinglist.domain.ShopItem
import com.handroid.shoppinglist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItemData(mapper.mapEntityToDbModel(shopItem))
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItemData(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItemData(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
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