package com.stackexchange.di.module

import androidx.annotation.NonNull
import com.stackexchange.base.StackExchangeParam
import com.stackexchange.base.domain.GeneralUseCase
import com.stackexchange.base.repository.StrategyFlowRepository
import com.stackexchange.di.scope.PerApplication
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.domain.usecase.StackExchangeUserInterActor
import com.stackexchange.error.ErrorContainer
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    @PerApplication
    fun provideStackExchangeInterActor(
        @NonNull repository: StrategyFlowRepository<List<StackExchangeUserEntity>, StackExchangeParam>,
        @NonNull errorContainer: ErrorContainer
    ): GeneralUseCase<StackExchangeParam, List<StackExchangeUserEntity>> =
        StackExchangeUserInterActor(repository, errorContainer)
}