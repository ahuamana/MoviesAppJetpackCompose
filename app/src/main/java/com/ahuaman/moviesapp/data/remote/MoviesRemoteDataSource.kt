package com.ahuaman.moviesapp.data.remote

import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import com.ahuaman.moviesapp.framework.network.BaseDataSource
import javax.inject.Inject

interface IMoviesRemoteDataSource {
    suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): PopularsMovieResponse

    suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ): MoviesDetailResponse

    suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String,
    ): PopularsMovieResponse

}


class MoviesRemoteDataSource @Inject constructor(
    private val iMoviesService: IMoviesService
) : BaseDataSource(), IMoviesRemoteDataSource {

    override suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ) = getResult (
        call = { iMoviesService.getPopularMovies(api_key, language, page) },
        forceError = false
    )

    override suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ) = getResult (
        call = { iMoviesService.getMovieDetail(api_key, language, id) },
        forceError = false
    )

    override suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String
    ) = getResult (
        call = { iMoviesService.searchMovie(query, api_key, language) },
        forceError = false
    )
}