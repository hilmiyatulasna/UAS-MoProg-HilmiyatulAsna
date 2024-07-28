package com.example.tugasandroid.data.repository

import com.example.tugasandroid.data.local.LocalMovie
import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(): Flow<List<LocalMovie>>
    fun getMovie(movieId: Int): Flow<LocalMovie>
    suspend fun insertMovie(movie: LocalMovie)
    suspend fun deleteMovie(movie: LocalMovie)
    suspend fun updateMovie(movie: LocalMovie)
    suspend fun fetchNowPlaying(): List<Movie>
    suspend fun fetchPopular(): List<Movie>
    suspend fun fetchTopRated(): List<Movie>
    suspend fun fetchUpcoming(): List<Movie>
    suspend fun fetchSearch(query: String): List<Movie>
    suspend fun fetchMovieDetail(movieId: Int): MovieDetail
}