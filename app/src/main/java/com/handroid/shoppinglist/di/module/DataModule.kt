package com.handroid.shoppinglist.di.module

import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.data.database.ShopItemDbModel
import com.handroid.shoppinglist.data.database.ShopListDao
import com.handroid.shoppinglist.di.annotation.ApplicationScope
import com.handroid.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListDao(impl: ShopItemDbModel): ShopListDao

    @ApplicationScope
    @Binds
    fun bindShopListRepository(
        impl: ShopListRepositoryImpl
    ): ShopListRepository
}