package com.stackexchange.presentation.stack_exchange

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stackexchange.HistoryObserver
import com.stackexchange.base.ResultResponse
import com.stackexchange.base.StackExchangeParam
import com.stackexchange.base.domain.GeneralUseCase
import com.stackexchange.domain.model.StackExchangeUserEntity
import com.stackexchange.error.ErrorEntity
import com.stackexchange.presentation.main.StackExchangeViewModel
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StackExchangeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: StackExchangeViewModel

    @Mock
    lateinit var stackExchangeUsersUseCase: GeneralUseCase<StackExchangeParam, List<StackExchangeUserEntity>>

    private val historyObserver: HistoryObserver<StackExchangeState> = HistoryObserver()

    @Mock
    lateinit var mockStateExchangeEntity: List<StackExchangeUserEntity>

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        subject = StackExchangeViewModel(stackExchangeUsersUseCase, testDispatcher)
        subject.state.observeForever(historyObserver)
    }

    @Test
    fun givenStateExchangeUsersAvailable_whenOnEventTriggered_thenStackExchangeUsersDataAvailable() {
        testScope.runBlockingTest {
            given(stackExchangeUsersUseCase.execute(StackExchangeParam.GetUsers))
                .willReturn(flowOf(ResultResponse.Success(mockStateExchangeEntity)))
            // when
            subject.handleEvent(StackExchangeEvent.GetUsers)

            // then
            historyObserver.assertNotEmpty()
            assertTrue(historyObserver.getHistoryItem().data is StackExchangeState.Data.Data)
        }
    }

    @Test
    fun givenStateExchangeUsersError_whenOnEventTriggered_thenStackExchangeUsersError() {
        testScope.runBlockingTest {
            val error = ErrorEntity.Unknown("Random Exception")
            given(stackExchangeUsersUseCase.execute(StackExchangeParam.GetUsers))
                .willReturn(flowOf(ResultResponse.Failure(error)))
            // when
            subject.handleEvent(StackExchangeEvent.GetUsers)

            // then
            historyObserver.assertNotEmpty()
            assertTrue(historyObserver.getHistoryItem().data is StackExchangeState.Data.NoData)
            assertTrue(historyObserver.getHistoryItem().baseState.error.isError())
        }
    }
}