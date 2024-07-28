package com.example.tugasandroid.data.remote

import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteSource(
    private val service: MovieDBService
) {
    suspend fun getNowPlaying(): List<Movie> {
        return withContext(Dispatchers.IO) {
            service.getNowPlaying().results
        }
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            service.getMovieDetail(movieId)
        }
    }

    suspend fun getPopular(): List<Movie> {
        return withContext(Dispatchers.IO) {
            service.getPopular().results
        }
    }

    suspend fun getTopRated(): List<Movie> {
        return withContext(Dispatchers.IO) {
            service.getTopRated().results
        }
    }

    suspend fun getUpcoming(): List<Movie> {
        return withContext(Dispatchers.IO) {
            service.getUpcoming().results
        }
    }

    suspend fun getSearchMovie(query: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            service.getSearchMovie(query).results
        }
    }
}