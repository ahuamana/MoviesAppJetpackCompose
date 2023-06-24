package com.ahuaman.moviesapp.data.repository

import com.ahuaman.moviesapp.data.remote.IMoviesRemoteDataSource
import com.ahuaman.moviesapp.data.remote.MoviesRemoteDataSource
import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import com.paparazziteam.securityapplicationapp.framework.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IMoviesRepository {
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
}

class MoviesRepository @Inject constructor(
    private val iMoviesRemoteDataSource: IMoviesRemoteDataSource
) : IMoviesRepository {

       override suspend fun getPopularMovies(
            api_key: String,
            language: String,
            page: Int
        ) = performNetworkFlow {
            iMoviesRemoteDataSource.getPopularMovies(api_key, language, page)
        }

        override suspend fun getMovieDetail(
            api_key: String,
            language: String,
            id: String
        ) = performNetworkFlow {
            iMoviesRemoteDataSource.getMovieDetail(api_key, language, id)
        }
}