package com.example.tugasandroid.di

import com.example.tugasandroid.data.local.MovieLocalSource
import com.example.tugasandroid.data.remote.MovieRemoteSource
import com.example.tugasandroid.data.repository.DefaultMovieRepository
import com.example.tugasandroid.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieLocalSource: MovieLocalSource, movieRemoteSource: MovieRemoteSource): MovieRepository {
        return DefaultMovieRepository(movieLocalSource, movieRemoteSource)
    }
}