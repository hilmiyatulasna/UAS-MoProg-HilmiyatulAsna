package com.example.tugasandroid.data.mapper

import com.example.tugasandroid.data.local.LocalMovie
import com.example.tugasandroid.data.remote.model.movie.Movie
import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail

fun MovieDetail.toLocalMovie() = LocalMovie(
    id = id,
    title = title,
    backdrop_path = backdrop_path
)