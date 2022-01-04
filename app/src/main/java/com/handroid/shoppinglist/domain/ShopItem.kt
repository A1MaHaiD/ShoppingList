package com.handroid.shoppinglist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list")
data class ShopItem(
    val name: String,
    val count: Int,
    val isSelected: Boolean,
    @PrimaryKey
    var id: Int = UNDEFINED_ID
){
    companion object{

        const val UNDEFINED_ID = -1
    }
}
