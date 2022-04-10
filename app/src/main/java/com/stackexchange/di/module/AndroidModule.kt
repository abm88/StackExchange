package com.stackexchange.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}
