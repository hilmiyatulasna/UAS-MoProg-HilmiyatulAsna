package com.example.tugasandroid.ui.route.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasandroid.data.mapper.toLocalMovie
import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail
import com.example.tugasandroid.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val movieId = savedStateHandle.get<Int>("movieId")

    private val _isFavorited = MutableStateFlow(false)
    val isFavorited = _isFavorited.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail = _movieDetail.asStateFlow()

    init {
        if (movieId != null) {
            val isMovieSaved = runBlocking { repository.getMovie(movieId).firstOrNull() }
            if (isMovieSaved != null) {
                _isFavorited.value = true
            }
            getMovieDetail(movieId)
        }
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _movieDetail.update { repository.fetchMovieDetail(movieId) }
        }
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            val movie = _movieDetail.value ?: return@launch
            if (_isFavorited.value) {
                repository.deleteMovie(movie.toLocalMovie())
                _isFavorited.value = false
            } else {
                repository.insertMovie(movie.toLocalMovie())
                _isFavorited.value = true
            }
        }
    }
}