package com.handroid.shoppinglist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.handroid.shoppinglist.domain.ShopItem

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_list ORDER BY id")
    fun getShopListData(): LiveData<List<ShopItem>>

    @Query("SELECT * FROM shop_list WHERE id == :shopItemId")
    fun getShopItemData(shopItemId: Int): LiveData<ShopItem>

    @Query("SELECT * FROM shop_list WHERE name == :name")
    fun getShopItemData(name: String): LiveData<ShopItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShopListData(shopList: List<ShopItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShopListData(shopList: List<ShopItem>)

    @Delete(entity = ShopItem::class)
    fun removeShopItemData(shopItemId: Int):LiveData<ShopItem>
}