package com.stackexchange.domain.usecase

import com.stackexchange.base.RepositoryStrategy
import com.stackexchange.base.ResultResponse
import com.stackexchange.base.StackExchangeParam
import com.stackexchange.base.repository.StrategyFlowRepository
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.error.GeneralHandlerImpl
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
class StackExchangeUserInterActorTest {

    private lateinit var subject: StackExchangeUserInterActor

    @Mock
    lateinit var topStoriesRepository: StrategyFlowRepository<List<StackExchangeUserEntity>, StackExchangeParam>

    private val errorContainer = GeneralHandlerImpl()

    private val param = StackExchangeParam.GetUsers

    private val strategy = RepositoryStrategy.OfflineFirst

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)


    @Before
    fun setup() {
        subject = StackExchangeUserInterActor(topStoriesRepository, errorContainer)
    }

    @Test
    fun givenStackExchangeResultSuccess_whenOnExecute_thenSuccessResponse() {
        testScope.launch {
            val entity = createEntity();
            given(topStoriesRepository.getResult(param, strategy)).willReturn(entity)
            // when
            val result = subject.execute(param, strategy).first()
            // then
            assertTrue(result.isSuccess())
            assertTrue(result.extractData()[0].userName == entity.first().extractData()[0].userName)
            assertTrue(result.extractData()[0].userId == entity.first().extractData()[0].userName)
            assertTrue(result.extractData()[0].avatar == entity.first().extractData()[0].avatar)
            assertTrue(result.extractData()[0].location == entity.first().extractData()[0].location)
        }
    }

    @Test
    fun givenStackExchangeResultFailure_whenOnExecute_thenFailureResponse() {
        testScope.launch {
            given(topStoriesRepository.getResult(param, strategy)).willReturn(flow {
                ResultResponse.Failure<StackExchangeUserEntity>(errorContainer.getError(Exception("Random Exception")))
            })
            // when
            val result = subject.execute(param, strategy).first()
            // then
            assertTrue(result.isFailure())
        }
    }

    private fun createEntity(): Flow<ResultResponse<List<StackExchangeUserEntity>>> = flow {
        ResultResponse.Success(
            arrayListOf<StackExchangeUserEntity>(
                StackExchangeUserEntity(
                    "Avatar", "UserID",
                    "UserName", "Reputation",
                    "tags", 0, "Location", "CreateDate", "link"
                )
            )
        )
    }
}