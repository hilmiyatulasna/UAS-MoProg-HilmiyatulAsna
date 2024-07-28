package com.example.tugasandroid.ui.route.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tugasandroid.data.remote.MovieDBService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackStack: () -> Unit
) {
    val movie by viewModel.movieDetail.collectAsState()
    val isFavorite by viewModel.isFavorited.collectAsState()

    if (movie != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = movie?.title ?: "") },
                    navigationIcon = {
                        IconButton(onClick = { onBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { viewModel.onFavoriteClick() }) {
                            Icon(
                                imageVector = when (isFavorite) {
                                    true -> Icons.Default.Favorite
                                    else -> Icons.Default.FavoriteBorder
                                },
                                contentDescription = "favorite icon"
                            )
                        }
                    }
                )
            },
            contentWindowInsets = WindowInsets(0.dp)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    model = MovieDBService.IMAGE_BASE_URL + movie?.backdrop_path,
                    contentDescription = "movie image",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Overview", style = MaterialTheme.typography.titleLarge)
                    Text(text = movie?.overview ?: "")
                }
            }
        }
    }
}