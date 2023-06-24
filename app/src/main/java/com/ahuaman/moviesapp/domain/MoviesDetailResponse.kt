package com.ahuaman.moviesapp.domain

import com.ahuaman.moviesapp.BuildConfig

data class MoviesDetailResponse(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

//Domain

data class MovieDetailDomain(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val belongs_to_collection: BelongsToCollectionDomain? = null,
    val budget: Int? = null,
    val genres: List<GenreDomain>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompanyDomain>? = null,
    val production_countries: List<ProductionCountryDomain>? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val runtimeWithMinutes: String? = null,
    val spoken_languages: List<SpokenLanguageDomain>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)

data class BelongsToCollectionDomain(
    val backdrop_path: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val poster_path: String? = null,
)

data class GenreDomain(
    val id: Int? = null,
    val name: String? = null,
)

data class ProductionCompanyDomain(
    val id: Int? = null,
    val logo_path: String? = null,
    val name: String? = null,
    val origin_country: String? = null,
)

data class ProductionCountryDomain(
    val iso_3166_1: String? = null,
    val name: String? = null,
)

data class SpokenLanguageDomain(
    val english_name: String? = null,
    val iso_639_1: String? = null,
    val name: String? = null,
)

//Mapper to Domain

fun MoviesDetailResponse.toDomainModel(): MovieDetailDomain {
    return MovieDetailDomain(
        adult = this.adult,
        backdrop_path = BuildConfig.IMAGE_URL + this.backdrop_path,
        belongs_to_collection = this.belongs_to_collection?.toDomainBelongsCollection(),
        budget = this?.budget,
        genres = this.genres?.toDomainGenre(),
        homepage = this.homepage,
        id = this.id,
        imdb_id = this.imdb_id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = BuildConfig.IMAGE_URL + this.poster_path,
        production_companies = this.production_companies?.toDomainProductionCompany(),
        production_countries = this.production_countries?.toDomainProductionCountry(),
        release_date = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        runtimeWithMinutes = "${this.runtime} minutos",
        spoken_languages = this.spoken_languages?.toDomainSpokenLan(),
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

fun BelongsToCollection.toDomainBelongsCollection(): BelongsToCollectionDomain {
    return BelongsToCollectionDomain(
        backdrop_path = this.backdrop_path,
        id = this.id,
        name = this.name,
        poster_path = this.poster_path
    )
}

fun List<Genre>.toDomainGenre(): List<GenreDomain> {
    return this.map {
        GenreDomain(
            id = it.id,
            name = it.name
        )
    }
}

fun List<ProductionCompany>.toDomainProductionCompany(): List<ProductionCompanyDomain> {
    return this.map {
        ProductionCompanyDomain(
            id = it.id,
            logo_path = it.logo_path,
            name = it.name,
            origin_country = it.origin_country
        )
    }
}

fun List<ProductionCountry>.toDomainProductionCountry(): List<ProductionCountryDomain> {
    return this.map {
        ProductionCountryDomain(
            iso_3166_1 = it.iso_3166_1,
            name = it.name
        )
    }
}

fun List<SpokenLanguage>.toDomainSpokenLan(): List<SpokenLanguageDomain> {
    return this.map {
        SpokenLanguageDomain(
            english_name = it.english_name,
            iso_639_1 = it.iso_639_1,
            name = it.name
        )
    }
}

