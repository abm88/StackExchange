package com.stackexchange.di.module

import android.content.Context
import com.stackexchange.base.*
import com.stackexchange.data.local.*
import com.stackexchange.data.model.StackExchangeDTO
import com.stackexchange.data.network.GetUsersAPI
import com.stackexchange.data.remote.StackExchangeRemoteDataSource
import com.stackexchange.di.scope.PerApplication
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.mapper.StackExchangeDTOToEntityMapper
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideStackExchangeRemoteDataSource(
        api: GetUsersAPI,
        mapper: Mapper<StackExchangeDTO, List<StackExchangeUserEntity>>
    ): ReadableDataSource<List<StackExchangeUserEntity>> =
        StackExchangeRemoteDataSource(api, mapper)

    @Provides
    fun provideStackExchangeLocalDataSource(
        dao: UsersDao
    ): WriteableDataSource<List<StackExchangeUserEntity>> =
        StackExchangeLocalDataSource(dao)

    @Provides
    fun provideUsersDao(appDataBase: AppDataBase): UsersDao =
        appDataBase.provideUsersDao()

    @Provides
    fun provideStackExchangeDTOToEntityMapper(): Mapper<StackExchangeDTO, List<StackExchangeUserEntity>> =
        StackExchangeDTOToEntityMapper()

    @Provides
    @PerApplication
    fun provideDataBase(context: Context): AppDataBase = AppDataBase.create(context)
}