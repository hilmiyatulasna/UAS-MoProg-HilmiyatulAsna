package com.example.tugasandroid.data.repository

import com.example.tugasandroid.data.local.LocalMovie
import com.example.tugasandroid.data.local.MovieLocalSource
import com.example.tugasandroid.data.remote.MovieRemoteSource
import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail
import kotlinx.coroutines.flow.Flow

class DefaultMovieRepository(
    private val movieLocalSource: MovieLocalSource,
    private val movieRemoteSource: MovieRemoteSource
): MovieRepository {
    override fun getAllMovies(): Flow<List<LocalMovie>> {
        return movieLocalSource.getAll()
    }

    override fun getMovie(movieId: Int): Flow<LocalMovie> {
        return movieLocalSource.get(movieId)
    }

    override suspend fun insertMovie(movie: LocalMovie) {
        movieLocalSource.insert(movie)
    }

    override suspend fun deleteMovie(movie: LocalMovie) {
        movieLocalSource.delete(movie)
    }

    override suspend fun updateMovie(movie: LocalMovie) {
        movieLocalSource.update(movie)
    }

    override suspend fun fetchNowPlaying(): List<Movie> {
        return movieRemoteSource.getNowPlaying()
    }

    override suspend fun fetchPopular(): List<Movie> {
        return movieRemoteSource.getPopular()
    }

    override suspend fun fetchTopRated(): List<Movie> {
        return movieRemoteSource.getTopRated()
    }

    override suspend fun fetchUpcoming(): List<Movie> {
        return movieRemoteSource.getUpcoming()
    }

    override suspend fun fetchSearch(query: String): List<Movie> {
        return movieRemoteSource.getSearchMovie(query)
    }

    override suspend fun fetchMovieDetail(movieId: Int): MovieDetail {
        return movieRemoteSource.getMovieDetail(movieId)
    }
}