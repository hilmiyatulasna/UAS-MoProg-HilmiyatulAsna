package com.example.tugasandroid.ui.route.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies = _popularMovies.asStateFlow()
    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()
    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies = _topRatedMovies.asStateFlow()
    private val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies = _upcomingMovies.asStateFlow()

    init {
        initializeData()
    }

    private fun initializeData() {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = repository.fetchPopular()
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowPlayingMovies.value = repository.fetchNowPlaying()
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRatedMovies.value = repository.fetchTopRated()
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _upcomingMovies.value = repository.fetchUpcoming()
        }
    }
}