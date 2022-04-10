package com.stackexchange.data.remote

import com.stackexchange.base.Mapper
import com.stackexchange.base.ReadableDataSource
import com.stackexchange.base.ResultResponse
import com.stackexchange.data.model.StackExchangeDTO
import com.stackexchange.data.network.GetUsersAPI
import com.stackexchange.domain.model.StackExchangeUserEntity
import javax.inject.Inject

class StackExchangeRemoteDataSource @Inject constructor(
    private val api: GetUsersAPI,
    private val mapper: Mapper<StackExchangeDTO, List<StackExchangeUserEntity>>
) : ReadableDataSource<List<StackExchangeUserEntity>>() {
    override suspend fun read(): ResultResponse<List<StackExchangeUserEntity>> =
        ResultResponse.Success(mapper.map(api.getUsers()))
}