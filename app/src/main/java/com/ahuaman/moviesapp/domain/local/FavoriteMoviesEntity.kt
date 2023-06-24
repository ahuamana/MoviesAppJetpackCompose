package com.ahuaman.moviesapp.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_movies")
data class FavoriteMoviesEntity(
   @PrimaryKey val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String,
)