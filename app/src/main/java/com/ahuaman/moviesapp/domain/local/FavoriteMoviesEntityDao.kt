package com.ahuaman.moviesapp.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesEntityDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAll(): Flow<List<FavoriteMoviesEntity>>

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    fun getById(id: Int): Flow<FavoriteMoviesEntity>

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteById(id: Int)

    //add
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMoviesEntity: FavoriteMoviesEntity)

}