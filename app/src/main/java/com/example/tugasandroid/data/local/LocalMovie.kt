package com.example.tugasandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class LocalMovie(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String,
    val title: String,
)