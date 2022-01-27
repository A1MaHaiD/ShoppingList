package com.handroid.shoppinglist.di.component

import android.content.Context
import com.handroid.shoppinglist.di.annotation.ApplicationScope
import com.handroid.shoppinglist.di.module.ShoppingViewModelModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [ShoppingViewModelModule::class]
)
interface ApplicationComponent {

    fun activityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}