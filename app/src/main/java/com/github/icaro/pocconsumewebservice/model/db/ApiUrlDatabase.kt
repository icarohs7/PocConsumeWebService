package com.github.icaro.pocconsumewebservice.model.db

import android.arch.persistence.room.*
import android.content.Context
import com.github.icaro.pocconsumewebservice.model.ApiUrl
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.coroutines.experimental.bg

@Database(entities = [ApiUrl::class], version = 1)
abstract class ApiUrlDatabase : RoomDatabase() {
    
    companion object {
        private var INSTANCE: ApiUrlDatabase? = null
        fun getInstance(context: Context): ApiUrlDatabase {
            return INSTANCE ?: {
                INSTANCE = Room
                    .databaseBuilder(context, ApiUrlDatabase::class.java, "app_database.db")
                    .build()
                INSTANCE!!
            }()
        }
        
        fun <T> dbTransactionAsync(context: Context, fn: (dao: ApiUrlDao) -> T): Deferred<T> {
            return bg { getInstance(context).apiUrlDao().let(fn) }
        }
    }
    
    abstract fun apiUrlDao(): ApiUrlDao
}