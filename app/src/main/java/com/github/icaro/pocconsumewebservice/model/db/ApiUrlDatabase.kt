package com.github.icaro.pocconsumewebservice.model.db

import android.arch.persistence.room.*
import android.content.Context
import com.github.icaro.pocconsumewebservice.model.entities.ApiUrl
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.coroutines.experimental.bg

@Database(entities = [ApiUrl::class], version = 1)
abstract class ApiUrlDatabase : RoomDatabase() {
    
    companion object {
        private var INSTANCE: ApiUrlDatabase? = null
        private fun getInstance(context: Context): ApiUrlDatabase {
            return INSTANCE ?: {
                INSTANCE = Room
                    .databaseBuilder(context, ApiUrlDatabase::class.java, "app_database.db")
                    .build()
                INSTANCE!!
            }()
        }
    
        operator fun <T> invoke(context: Context, fn: (dao: ApiUrlDao) -> T): Deferred<T> {
            return bg {
                fn(ApiUrlDatabase.getInstance(context).apiUrlDao())
            }
        }
    }
    
    abstract fun apiUrlDao(): ApiUrlDao
}