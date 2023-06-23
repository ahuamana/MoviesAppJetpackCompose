package com.ahuaman.moviesapp.domain



data class MovieDomain(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
)


data class MovieEntity(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
)

data class PopularsMovieResponse(
    val page: Int,
    val results: List<MovieDomain>,
    val total_pages: Int,
    val total_results: Int
)

fun List<MovieEntity>.toDomainModel(): List<MovieDomain> {
    return map {
        MovieDomain(
            id = it.id,
            poster_path = it.poster_path,
            overview = it.overview,
            title = it.title,
            vote_average = it.vote_average
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
