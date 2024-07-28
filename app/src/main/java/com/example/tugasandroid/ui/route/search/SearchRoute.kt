package com.example.tugasandroid.ui.route.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tugasandroid.data.remote.MovieDBService
import com.example.tugasandroid.data.remote.model.movie.Movie

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    toDetail: (Int) -> Unit
) {
    val query by viewModel.query.collectAsState()
    val movies by viewModel.searchMovies.collectAsState()
    val searchFlow by viewModel.searchFlow.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "search icon")
                BasicTextField(
                    value = query,
                    onValueChange = { viewModel.onQueryChanged(it)},
                ) { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (query.isEmpty()) {
                            Text(text = "Search")
                        }
                        innerTextField()
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = movies) { movie ->
                SearchMovieItem(
                    movie = movie,
                    onMovieClick = { toDetail(it.id) }
                )
            }
        }
    }
}

@Composable
fun SearchMovieItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick(movie) },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 100.dp, height = 140.dp)
                .clip(RoundedCornerShape(4.dp)),
            model = MovieDBService.IMAGE_BASE_URL + movie.backdrop_path,
            contentDescription = "movie image",
            contentScale = ContentScale.Crop
        )
        Text(text = movie.title)
    }
}