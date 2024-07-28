package com.example.tugasandroid.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<LocalMovie>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun get(movieId: Int): Flow<LocalMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: LocalMovie)

    @Delete
    suspend fun delete(movie: LocalMovie)

    @Update
    suspend fun update(movie: LocalMovie)

}
