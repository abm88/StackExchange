package com.stackexchange.data.network

import com.stackexchange.data.model.StackExchangeDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUsersAPI {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1,
                         @Query("pagesize") pagesize: Int = 20,
                         @Query("order") order: String = "desc",
                         @Query("sort") sort: String = "reputation",
                         @Query("site") site: String = "stackoverflow"
    ): StackExchangeDTO
}