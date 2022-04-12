package com.stackexchange.domain.repository

import com.stackexchange.base.*
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.error.GeneralHandlerImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StackExchangeRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    lateinit var subject: StackExchangeRepository

    @Mock
    lateinit var localDataSource: WriteableDataSource<List<StackExchangeUserEntity>>

    @Mock
    lateinit var remoteDataSource: ReadableDataSource<List<StackExchangeUserEntity>>

    @Before
    fun setup(){
        subject = StackExchangeRepository(localDataSource, remoteDataSource, GeneralHandlerImpl())
    }

    @Test
    fun givenLocalDataAvailable_abd_remoteDataAvailable_whenGetResult_thenResultIsSuccess(){
        testScope.launch {
            given(localDataSource.read("")).willReturn(getLocalList())
            given(remoteDataSource.read()).willReturn(getRemoteList())
            // when
            val result = subject.getResult(StackExchangeParam.GetUsers,RepositoryStrategy.OfflineFirst ).toList()

            // then
            then(localDataSource).should(atLeastOnce()).write(any())
            assertTrue(result.size == 2)
        }
    }

    private fun getRemoteList(): ResultResponse<List<StackExchangeUserEntity>> =
        ResultResponse.Success(
            arrayListOf<StackExchangeUserEntity>(
                StackExchangeUserEntity(
                    "RemoteAvatar", "RemoteUserID",
                    "RemoteUserName", "RemoteReputation",
                    "RemoteTags", 0, "RemoteLocation", "RemoteCreateDate", "remoteRemoteCreateDate"
                )
            )
        )

    private fun getLocalList(): ResultResponse<List<StackExchangeUserEntity>> =
        ResultResponse.Success(
            arrayListOf<StackExchangeUserEntity>(
                StackExchangeUserEntity(
                    "Avatar", "UserID",
                    "UserName", "Reputation",
                    "tags", 0, "Location", "CreateDate","link"
                )
            )
        )

}