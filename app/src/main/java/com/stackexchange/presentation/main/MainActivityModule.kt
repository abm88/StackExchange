package com.stackexchange.presentation.main

import com.stackexchange.di.scope.PerFragment
import com.stackexchange.presentation.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindsMainActivity(): MainActivity

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindUserDetailFragment(): DetailFragment

}