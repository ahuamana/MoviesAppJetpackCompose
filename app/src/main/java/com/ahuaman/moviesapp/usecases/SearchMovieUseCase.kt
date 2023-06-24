package com.ahuaman.moviesapp.usecases

import com.ahuaman.moviesapp.data.repository.IMoviesRepository
import com.ahuaman.moviesapp.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        query: String,
        api_key: String,
        language: String
    ) = moviesRepository.searchMovie(query, api_key, language).map {
        it.results.toDomainModel()
    }
}
