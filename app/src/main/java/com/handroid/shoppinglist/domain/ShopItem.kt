package com.handroid.shoppinglist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list")
data class ShopItem(
    @PrimaryKey
    val id:Int,
    @PrimaryKey
    val name:String,
    val count: Int,
    val isSelected:Boolean
)
