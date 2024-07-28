package com.example.tugasandroid.di

import android.content.Context
import androidx.room.Room
import com.example.tugasandroid.data.local.MovieDao
import com.example.tugasandroid.data.local.MovieDatabase
import com.example.tugasandroid.data.local.MovieLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()

    @Singleton
    @Provides
    fun provideMovieLocalSource(movieDao: MovieDao) = MovieLocalSource(movieDao)
}