package com.ahuaman.moviesapp.data.remote

import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

interface IMoviesService {
    suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): Response<PopularsMovieResponse>

    suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ): Response<MoviesDetailResponse>
}


class MoviesServiceImpl @Inject constructor(
    private val moviesService: MoviesService
) : IMoviesService{
    override suspend fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): Response<PopularsMovieResponse> {
        return moviesService.getPopularMovies(api_key, language, page)
    }

    override suspend fun getMovieDetail(
        api_key: String,
        language: String,
        id: String
    ): Response<MoviesDetailResponse> {
        return moviesService.getMovieDetail(api_key =  api_key, language =  language, id = id)
    }
}