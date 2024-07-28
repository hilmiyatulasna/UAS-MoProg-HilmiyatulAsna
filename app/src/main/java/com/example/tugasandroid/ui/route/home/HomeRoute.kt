package com.example.tugasandroid.ui.route.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tugasandroid.data.remote.MovieDBService
import com.example.tugasandroid.data.remote.model.movie.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    toDetail: (Int) -> Unit
) {
    val popularList by viewModel.popularMovies.collectAsState()
    val topRatedList by viewModel.topRatedMovies.collectAsState()
    val upcomingList by viewModel.upcomingMovies.collectAsState()
    val nowPlayingList by viewModel.nowPlayingMovies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") })
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "Now Playing",
                    style = MaterialTheme.typography.titleMedium
                )
                HomeRowList(list = nowPlayingList, onMovieClick = { toDetail(it.id) })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Popular",
                    style = MaterialTheme.typography.titleMedium
                )
                HomeRowList(list = popularList, onMovieClick = { toDetail(it.id) })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Top Rated",
                    style = MaterialTheme.typography.titleMedium
                )
                HomeRowList(list = topRatedList, onMovieClick = { toDetail(it.id) })
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Upcoming",
                    style = MaterialTheme.typography.titleMedium
                )
                HomeRowList(list = upcomingList, onMovieClick = { toDetail(it.id) })
            }
        }
    }
}

@Composable
fun HomeRowList(
    list: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.height(260.dp)
    ) {
        items(items = list) { movie ->
            Column(
                modifier = Modifier
                    .width(140.dp)
                    .clickable { onMovieClick(movie) }
            ) {
                AsyncImage(
                    model = MovieDBService.IMAGE_BASE_URL + movie.backdrop_path,
                    contentDescription = "movie image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 140.dp, height = 200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
                Text(text = movie.title)
            }
        }
    }
}