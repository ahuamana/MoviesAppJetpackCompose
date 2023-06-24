package com.ahuaman.moviesapp.data.remote

import com.ahuaman.moviesapp.domain.MoviesDetailResponse
import com.ahuaman.moviesapp.domain.PopularsMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    //https://api.themoviedb.org/3/movie/popular?api_key=82ca5982f2873c2d38a664b67f135d79&language=es-ES&page=1
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<PopularsMovieResponse>

    //https://api.themoviedb.org/3/movie/343611?api_key=82ca5982f2873c2d38a664b67f135d79&language=es-ES

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,

    ): Response<MoviesDetailResponse>

    //https://api.themoviedb.org/3/search/movie?query=Jack+Reacher&api_key=API_KEY'
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Response<PopularsMovieResponse>

}