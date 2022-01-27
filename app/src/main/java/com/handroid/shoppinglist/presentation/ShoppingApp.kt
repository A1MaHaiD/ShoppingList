package com.handroid.shoppinglist.presentation

import android.app.Application
import com.handroid.shoppinglist.di.component.DaggerApplicationComponent

class ShoppingApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}