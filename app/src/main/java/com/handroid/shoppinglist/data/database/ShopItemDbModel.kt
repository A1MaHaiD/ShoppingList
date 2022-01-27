package com.handroid.shoppinglist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "shop_items")
data class ShopItemDbModel @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val isSelected: Boolean
)
