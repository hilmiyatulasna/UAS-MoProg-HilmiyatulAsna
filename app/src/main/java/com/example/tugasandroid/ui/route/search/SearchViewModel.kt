package com.example.tugasandroid.ui.route.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    val searchFlow = _query
        .debounce(1000L)
        .onEach { getSearchMovies(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    private val _searchMovies = MutableStateFlow<List<Movie>>(emptyList())
    val searchMovies = _searchMovies.asStateFlow()

    private fun getSearchMovies(query: String) {
        viewModelScope.launch {
            _searchMovies.value = repository.fetchSearch(query)
        }
    }

    fun onQueryChanged(query: String) {
        _query.value = query
    }
}