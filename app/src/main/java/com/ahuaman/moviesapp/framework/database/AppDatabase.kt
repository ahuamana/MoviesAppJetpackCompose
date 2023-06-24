package com.ahuaman.moviesapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntityDao
import javax.inject.Inject

@Database(entities = [FavoriteMoviesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesEntityDao
}