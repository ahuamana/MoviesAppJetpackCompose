package com.ahuaman.moviesapp.framework.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideFavoriteMoviesDao(appDatabase: AppDatabase) = appDatabase.favoriteMoviesDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "MoviesApp"
    ).fallbackToDestructiveMigration().build()
}