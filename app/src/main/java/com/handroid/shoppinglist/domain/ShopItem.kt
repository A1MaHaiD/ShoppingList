package com.handroid.shoppinglist.domain

data class ShopItem(
    val id:Int,
    val name:String,
    val count: Int,
    val isSelected:Boolean
)
