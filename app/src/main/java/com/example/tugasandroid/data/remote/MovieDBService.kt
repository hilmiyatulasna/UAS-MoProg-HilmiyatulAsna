package com.example.tugasandroid.data.remote

import com.example.tugasandroid.data.remote.model.movie.detail.MovieDetail
import com.example.tugasandroid.data.remote.model.response.NowPlayingResponse
import com.example.tugasandroid.data.remote.model.response.PopularResponse
import com.example.tugasandroid.data.remote.model.response.SearchResponse
import com.example.tugasandroid.data.remote.model.response.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @Headers("Authorization: Bearer $API_KEY")
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetail

    @Headers("Authorization: Bearer $API_KEY")
    @GET("movie/popular")
    suspend fun getPopular(): PopularResponse

    @Headers("Authorization: Bearer $API_KEY")
    @GET("movie/now_playing")
    suspend fun getNowPlaying(): NowPlayingResponse

    @Headers("Authorization: Bearer $API_KEY")
    @GET("movie/top_rated")
    suspend fun getTopRated(): PopularResponse

    @Headers("Authorization: Bearer $API_KEY")
    @GET("movie/upcoming")
    suspend fun getUpcoming(): UpcomingResponse

    @Headers("Authorization: Bearer $API_KEY")
    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query ("query") query: String
    ): SearchResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZjI2MmZmYWI3NzVhZDZmM2IzMzc5MDI2Y2NlYWI0ZCIsIm5iZiI6MTcyMTAyNDAxMS40ODUyNTQsInN1YiI6IjYzNmY2YzNjY2E0ZjY3MDBjNjhkNzhjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TSH1fyDgXWJtFsusg7I9Hy_PTCmZra_IOZ3uggQlAWo"
    }
}