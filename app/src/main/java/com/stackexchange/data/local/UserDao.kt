package com.stackexchange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stackexchange.domain.model.StackExchangeUserEntity

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun write(item: List<StackExchangeUserEntity>)

    @Query("SELECT * FROM users ORDER BY userId desc")
    suspend fun readAll(): List<StackExchangeUserEntity>

    @Query("SELECT * FROM users WHERE userName LIKE '%' || :query ||'%'  ORDER BY userId desc")
    suspend fun read(query: String): List<StackExchangeUserEntity>
}