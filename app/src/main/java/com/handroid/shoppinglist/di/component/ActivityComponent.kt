package com.handroid.shoppinglist.di.component

import com.handroid.shoppinglist.di.annotation.qualifier.CountQualifier
import com.handroid.shoppinglist.di.annotation.qualifier.IdQualifier
import com.handroid.shoppinglist.di.annotation.qualifier.IsSelectedQualifier
import com.handroid.shoppinglist.di.annotation.qualifier.NameQualifier
import com.handroid.shoppinglist.di.module.DataModule
import com.handroid.shoppinglist.presentation.ui.activities.ShoppingListActivity
import com.handroid.shoppinglist.presentation.ui.fragments.ShopItemFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [DataModule::class]
)
interface ActivityComponent {

    fun inject(activity: ShoppingListActivity)

    fun inject(fragment: ShopItemFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance @IdQualifier id: Int,
            @BindsInstance @NameQualifier name: String,
            @BindsInstance @CountQualifier count: Int,
            @BindsInstance @IsSelectedQualifier isSelected: Boolean
        ): ActivityComponent
    }
}