package com.handroid.shoppinglist.domain

import javax.inject.Inject

data class ShopItem @Inject constructor(
    val name: String,
    val count: Int,
    val isSelected: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
