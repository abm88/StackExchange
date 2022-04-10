package com.stackexchange.domain.repository

import com.stackexchange.base.*
import com.stackexchange.base.repository.StrategyFlowRepository
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.error.ErrorContainer
import javax.inject.Inject

class StackExchangeRepository @Inject constructor(
    private val localDataSource: WriteableDataSource<List<StackExchangeUserEntity>>,
    private val remoteDataSource: ReadableDataSource<List<StackExchangeUserEntity>>,
    errorContainer: ErrorContainer
) : StrategyFlowRepository<List<StackExchangeUserEntity>, StackExchangeParam>(errorContainer) {

    override suspend fun getLocal(param: StackExchangeParam): ResultResponse<List<StackExchangeUserEntity>> =
        when (param) {
            StackExchangeParam.GetUsers -> {
                localDataSource.read("")
            }
            is StackExchangeParam.SearchUsers -> {
                localDataSource.read(param.data)
            }
            else -> {
                localDataSource.read("")
            }
        }

    override suspend fun getRemote(param: StackExchangeParam): ResultResponse<List<StackExchangeUserEntity>> =
        remoteDataSource.read()

    override suspend fun saveRemote(data: List<StackExchangeUserEntity>) {
        localDataSource.write(data)
    }
}