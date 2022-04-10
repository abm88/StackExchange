package com.stackexchange.data.local

import com.stackexchange.base.*
import com.stackexchange.domain.model.StackExchangeUserEntity
import javax.inject.Inject

class StackExchangeLocalDataSource @Inject constructor(
    private val userDao: UsersDao
) : WriteableDataSource<List<StackExchangeUserEntity>>() {
    override suspend fun write(entities: List<StackExchangeUserEntity>) {
        userDao.write(entities)
    }

    override suspend fun read(query: String): ResultResponse<List<StackExchangeUserEntity>> =
        if (query.isEmpty()) {
            ResultResponse.Success(userDao.readAll())
        } else {
            ResultResponse.Success(userDao.read(query))
        }

}