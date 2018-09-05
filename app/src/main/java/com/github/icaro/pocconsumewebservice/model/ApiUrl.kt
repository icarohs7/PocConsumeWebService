package com.github.icaro.pocconsumewebservice.model

import android.arch.persistence.room.*

@Entity(tableName = "apiurl")
data class ApiUrl(val url: String, @PrimaryKey val id: Int = 1)