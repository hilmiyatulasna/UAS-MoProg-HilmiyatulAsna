package com.example.tugasandroid.di

import com.example.tugasandroid.data.remote.MovieDBService
import com.example.tugasandroid.data.remote.MovieRemoteSource
import com.example.tugasandroid.data.repository.DefaultMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MovieDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDBService(retrofit: Retrofit): MovieDBService {
        return retrofit.create(MovieDBService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteSource(movieDBService: MovieDBService): MovieRemoteSource {
        return MovieRemoteSource(movieDBService)
    }

}