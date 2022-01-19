package com.handroid.shoppinglist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopListData(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItemData(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id == :shopItemId")
    fun deleteShopItemData(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id == :shopItemId LIMIT 1")
    fun getShopItemData(shopItemId: Int): ShopItemDbModel
}