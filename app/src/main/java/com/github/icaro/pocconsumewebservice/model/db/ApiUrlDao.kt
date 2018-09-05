package com.github.icaro.pocconsumewebservice.model.db

import android.arch.persistence.room.*
import com.github.icaro.pocconsumewebservice.model.entities.ApiUrl

@Dao
interface ApiUrlDao {
    
    @Query("SELECT * FROM apiurl LIMIT 1")
    fun queryUrl(): ApiUrl?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(apiUrl: ApiUrl)
    
    @Update
    fun update(apiUrl: ApiUrl)
}