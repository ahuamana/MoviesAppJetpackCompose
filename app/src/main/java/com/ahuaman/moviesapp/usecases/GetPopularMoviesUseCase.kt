package com.ahuaman.moviesapp.usecases

import com.ahuaman.moviesapp.data.repository.IMoviesRepository
import com.ahuaman.moviesapp.data.repository.MoviesRepository
import com.ahuaman.moviesapp.domain.toDomainModel
import com.ahuaman.moviesapp.domain.toEntityModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        api_key: String,
        language: String,
        page: Int
    ) = iMoviesRepository.getPopularMovies(api_key, language, page).map {
        it.results.toDomainModel()
    }
}