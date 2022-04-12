package com.stackexchange.di.module

import com.stackexchange.base.*
import com.stackexchange.base.repository.StrategyFlowRepository
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.domain.repository.StackExchangeRepository
import com.stackexchange.error.ErrorContainer
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesStackExchangeRepository(
        localDataSource: WriteableDataSource<List<StackExchangeUserEntity>>,
        remoteDataSource: ReadableDataSource<List<StackExchangeUserEntity>>,
        errorContainer: ErrorContainer
    ): StrategyFlowRepository<List<StackExchangeUserEntity>, StackExchangeParam> =
        StackExchangeRepository(localDataSource, remoteDataSource, errorContainer)
}