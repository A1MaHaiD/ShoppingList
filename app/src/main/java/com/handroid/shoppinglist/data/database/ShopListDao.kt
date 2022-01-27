package com.handroid.shoppinglist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopListData(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItemData(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id == :shopItemId")
    suspend fun deleteShopItemData(shopItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id == :shopItemId LIMIT 1")
    suspend fun getShopItemData(shopItemId: Int): ShopItemDbModel
}