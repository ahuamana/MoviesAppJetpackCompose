package com.ahuaman.moviesapp.data.remote

import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import com.ahuaman.moviesapp.framework.network.BaseDataSource
import com.paparazziteam.securityapplicationapp.framework.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IMoviesRemoteDataSource {
    suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String,
    ): Flow<PopularsMovieResponse>

}


class MoviesRemoteDataSource @Inject constructor(
    private val iMoviesService: IMoviesService
) :IMoviesRemoteDataSource {

    override suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ) = performNetworkFlow {
        iMoviesService.getPopularMovies(api_key, language, page)
    }

    override suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ) = performNetworkFlow {
        iMoviesService.getMovieDetail(api_key, language, id)
    }

    override suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String
    ) = performNetworkFlow {
        iMoviesService.searchMovie(query, api_key, language)
    }
}