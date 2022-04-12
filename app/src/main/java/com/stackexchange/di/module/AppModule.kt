package com.stackexchange.di.module

import com.stackexchange.di.scope.DefaultDispatcher
import com.stackexchange.di.scope.IODispatcher
import com.stackexchange.di.scope.MainDispatcher
import com.stackexchange.di.scope.UnconfinedDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
abstract class AppModule {

    companion object {
        @MainDispatcher
        @Provides
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @DefaultDispatcher
        @Provides
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @IODispatcher
        @Provides
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @UnconfinedDispatcher
        @Provides
        fun provideUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
    }

}