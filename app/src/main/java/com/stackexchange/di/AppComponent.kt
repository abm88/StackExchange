package com.stackexchange.di

import android.app.Application
import com.stackexchange.di.module.*
import com.stackexchange.di.scope.PerApplication
import com.stackexchange.presentation.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@PerApplication
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        DataModule::class,
        RepositoryModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}