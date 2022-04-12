package com.stackexchange.data.local

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stackexchange.domain.model.StackExchangeUserEntity


@Database(
    entities = [(StackExchangeUserEntity::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters((StringToListTypeConvertor::class))
abstract class AppDataBase : RoomDatabase() {

    abstract fun provideUsersDao(): UsersDao

    companion object {
        fun create(context: Context): AppDataBase =
            Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "stackExchange"
            ).allowMainThreadQueries().build()

        fun getTestInstance(context: Context) =
            Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
                .allowMainThreadQueries()
                .build()
    }

}

class StringToListTypeConvertor {

    @TypeConverter
    fun convertToString(input: List<String>): String {
        val gson = Gson()
        return gson.toJson(input)
    }

    @TypeConverter
    fun fromString(value: String): ArrayList<String?>? {
        val listType= object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}