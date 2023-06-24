package com.ahuaman.moviesapp.domain

import com.ahuaman.moviesapp.BuildConfig


data class PopularsMovieResponse(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDomain(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String? = null,
)


data class MovieEntity(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String? = null,
)

fun List<MovieEntity>.toDomainModel(): List<MovieDomain> {
    return map {
        MovieDomain(
            id = it.id,
            poster_path = BuildConfig.IMAGE_URL + it.poster_path, // Here we are adding the base url to the poster_path
            overview = it.overview,
            title = it.title,
            vote_average = it.vote_average,
            release_date = it.release_date
        )
    }
}

fun List<MovieDomain>.toEntityModel(): List<MovieEntity> {
    return map {
        MovieEntity(
            id = it.id,
            poster_path = it.poster_path,
            overview = it.overview,
            title = it.title,
            vote_average = it.vote_average
        )
    }
}

