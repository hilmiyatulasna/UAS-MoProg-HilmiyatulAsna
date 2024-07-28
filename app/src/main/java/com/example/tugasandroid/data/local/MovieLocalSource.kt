package com.example.tugasandroid.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieLocalSource(
    private val movieDao: MovieDao
) {
    fun getAll(): Flow<List<LocalMovie>> = movieDao.getAll()
    fun get(id: Int): Flow<LocalMovie> = movieDao.get(id)
    suspend fun insert(movie: LocalMovie) {
        withContext(Dispatchers.IO) {
            movieDao.insert(movie)
        }
    }
    suspend fun delete(movie: LocalMovie) {
        withContext(Dispatchers.IO) {
            movieDao.delete(movie)
        }
    }
    suspend fun update(movie: LocalMovie) {
        withContext(Dispatchers.IO) {
            movieDao.update(movie)
        }
    }
}