package com.ahuaman.moviesapp.usecases.data

import com.ahuaman.moviesapp.data.repository.IMoviesRepository
import com.ahuaman.moviesapp.domain.MovieEntity
import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
import com.ahuaman.moviesapp.usecases.fakes.FakeValueApi.popularResponseFake
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds




class FakeRepositorySuccessApi : IMoviesRepository {
    override suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse> {
        return flow {
            delay(2.seconds) // Simulate network delay
            emit(popularResponseFake())
        }
    }

    override suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse> {
        return flow {
            emit(MoviesDetailResponse(
                adult = false,
                backdrop_path = "",
                belongs_to_collection = null,
                budget = 1,
                genres = listOf(),
                homepage = "",
                id = 1,
                imdb_id = "",
                original_language = "",
                original_title = "",
                overview = "",
                popularity = 1.0,
                poster_path = "",
                production_companies = listOf(),
                production_countries = listOf(),
                release_date = "",
                revenue = 1,
                runtime = 1,
                spoken_languages = listOf(),
                status = "",
                tagline = "",
                title = "",
                video = false,
                vote_average = 1.0,
                vote_count = 1
            ))
        }
    }

    override suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return flow {
            emit(PopularsMovieResponse(
                page = 1,
                results = listOf(),
                total_pages = 1,
                total_results = 1
            ))
        }
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>> {
        return flow {
            emit(listOf(
                FavoriteMoviesEntity(
                    id = 1,
                    title = "",
                    overview = "",
                    poster_path = "",
                    vote_average = 1.0f,
                    release_date = ""
                )
            ))
        }
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity> {
        return flow {
            emit(FavoriteMoviesEntity(
                id = 1,
                title = "",
                overview = "",
                poster_path = "",
                vote_average = 1.0f,
                release_date = ""
            ))
        }
    }

    override suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity) {
        // Do nothing
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        // Do nothing
    }
}