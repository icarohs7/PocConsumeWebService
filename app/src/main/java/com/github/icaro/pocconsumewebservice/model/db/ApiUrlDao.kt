package com.github.icaro.pocconsumewebservice.model.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.github.icaro.pocconsumewebservice.model.ApiUrl

@Dao
interface ApiUrlDao {
    
    @Query("SELECT * FROM apiurl WHERE id = 1")
    fun queryUrl(): LiveData<ApiUrl>
    
    @Insert
    fun insert(apiUrl: ApiUrl)
    
    @Update
    fun update(apiUrl: ApiUrl)
}