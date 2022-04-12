package com.stackexchange

import com.stackexchange.di.AppComponent
import com.stackexchange.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class StackExchangeApplication : DaggerApplication(){
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}