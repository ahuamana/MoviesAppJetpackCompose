package com.ahuaman.moviesapp.usecases

import com.ahuaman.moviesapp.data.repository.IMoviesRepository
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
import javax.inject.Inject

class InsertFavoriteMovieUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(favoriteMoviesEntity: FavoriteMoviesEntity) = repository.insertFavoriteMovie(favoriteMoviesEntity)
}