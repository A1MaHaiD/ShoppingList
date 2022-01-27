package com.handroid.shoppinglist.di.component

import android.app.Application
import com.handroid.shoppinglist.di.annotation.ApplicationScope
import com.handroid.shoppinglist.di.module.DataModule
import com.handroid.shoppinglist.di.module.ViewModelModule
import com.handroid.shoppinglist.presentation.ui.activities.ShoppingListActivity
import com.handroid.shoppinglist.presentation.ui.fragments.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(activity: ShoppingListActivity)

    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}