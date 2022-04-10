package com.stackexchange.domain.usecase

import com.stackexchange.base.RepositoryStrategy
import com.stackexchange.base.ResultResponse
import com.stackexchange.base.StackExchangeParam
import com.stackexchange.base.domain.GeneralUseCase
import com.stackexchange.base.repository.StrategyFlowRepository
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.error.ErrorContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class StackExchangeUserInterActor(
    private val topStoriesRepository: StrategyFlowRepository<List<StackExchangeUserEntity>, StackExchangeParam>,
    errorContainer: ErrorContainer
) : GeneralUseCase<StackExchangeParam, List<StackExchangeUserEntity>>(errorContainer) {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun buildFlow(
        param: StackExchangeParam,
        strategy: RepositoryStrategy
    ): Flow<ResultResponse<List<StackExchangeUserEntity>>> = when(param){
        StackExchangeParam.GetUsers -> {
            topStoriesRepository.getResult(
                param, RepositoryStrategy.OfflineFirst
            )
        }
        is StackExchangeParam.SearchUsers -> {
            topStoriesRepository.getResult(
                param, RepositoryStrategy.Local
            )
        }
        else -> {
            throw IllegalArgumentException("Unknown event: $param")
        }

    }
}