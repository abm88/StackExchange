package com.stackexchange.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackexchange.di.ViewModelKey
import com.stackexchange.factory.AppViewModelFactory
import com.stackexchange.presentation.main.StackExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(StackExchangeViewModel::class)
    abstract fun bindStackExchangeViewModel(viewModel: StackExchangeViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory : AppViewModelFactory) : ViewModelProvider.Factory

}
