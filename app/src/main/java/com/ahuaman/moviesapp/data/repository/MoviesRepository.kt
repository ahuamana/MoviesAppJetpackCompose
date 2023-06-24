package com.ahuaman.moviesapp.data.repository

import com.ahuaman.moviesapp.data.local.IFavoriteMoviesLocalDataSource
import com.ahuaman.moviesapp.data.remote.IMoviesRemoteDataSource
import com.ahuaman.moviesapp.data.remote.MoviesRemoteDataSource
import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
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

    suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String,
    ): Flow<PopularsMovieResponse>


    //Local
    fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity>
    suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity)
    suspend fun deleteFavoriteMovie(id: Int)

}

class MoviesRepository @Inject constructor(
    private val remote: IMoviesRemoteDataSource,
    private val local: IFavoriteMoviesLocalDataSource
) : IMoviesRepository {

       override suspend fun getPopularMovies(
            api_key: String,
            language: String,
            page: Int
        ) = performNetworkFlow {
           remote.getPopularMovies(api_key, language, page)
        }

        override suspend fun getMovieDetail(
            api_key: String,
            language: String,
            id: String
        ) = performNetworkFlow {
            remote.getMovieDetail(api_key, language, id)
        }

    override suspend fun searchMovie(
        query: String,
        api_key: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return performNetworkFlow {
            remote.searchMovie(query, api_key, language)
        }
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>> {
        return local.getFavoriteMovies()
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity> {
        return local.getFavoriteMovieById(id)
    }

    override suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity) {
        local.insertFavoriteMovie(favoriteMoviesEntity)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        local.deleteFavoriteMovie(id)
    }
}