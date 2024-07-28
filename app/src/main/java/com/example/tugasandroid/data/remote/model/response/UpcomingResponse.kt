package com.example.tugasandroid.data.remote.model.response

import com.example.tugasandroid.data.remote.model.movie.Dates
import com.example.tugasandroid.data.remote.model.movie.Movie

data class UpcomingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)