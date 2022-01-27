package com.handroid.shoppinglist.di.module

import android.app.Application
import com.handroid.shoppinglist.data.ShopListRepositoryImpl
import com.handroid.shoppinglist.data.database.AppDatabase
import com.handroid.shoppinglist.data.database.ShopListDao
import com.handroid.shoppinglist.di.annotation.ApplicationScope
import com.handroid.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(
        impl: ShopListRepositoryImpl
    ): ShopListRepository


    companion object {
        @ApplicationScope
        @Provides
        fun bindShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}