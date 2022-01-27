package com.handroid.shoppinglist.di.module

import androidx.lifecycle.ViewModel
import com.handroid.shoppinglist.di.annotation.ViewModelKey
import com.handroid.shoppinglist.presentation.view_models.ShopItemViewModel
import com.handroid.shoppinglist.presentation.view_models.ShoppingListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ShoppingViewModelModule {

    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    @Binds
    fun bindShopItemViewModel(impl: ShopItemViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ShoppingListViewModel::class)
    @Binds
    fun bindShoppingListViewModel(impl: ShoppingListViewModel): ViewModel
}