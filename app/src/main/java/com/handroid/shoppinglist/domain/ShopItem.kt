package com.handroid.shoppinglist.domain

data class  ShopItem(
    val name: String,
    val count: Int,
    val isSelected: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
