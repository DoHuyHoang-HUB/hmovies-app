package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.annotations.ImageAnnotation
import com.codingtok.hmovies.data.annotations.Rated
import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.enums.ProductionStatus
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Movie(
    val id: Int,
    val title: String,
    @Json(name = "backdrop_path")
    @ImageAnnotation
    val backdrop: Image?,
    @Json(name = "original_language")
    val originalLanguage: String,
    val genres: List<Genres>,
    @Json(name = "vote_average")
    val voteAverage: Float,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: Date?,
    val adult: Boolean,
    val popularity: Float,
    @Json(name = "vote_count")
    val voteCount: Long,
    val video: Boolean,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Collection.Slim?,
    val budget: Long,
    val revenue: Long,
    val runtime: Int,
    val homepage: String,
    @Json(name = "imdb_id")
    val imdbId: String?,
    val status: ProductionStatus,
    val tagline: String,
    @Json(name = "production_companies")
    val productionCompanies: List<Company>,
    @Json(name = "production_countries")
    val productionCountries: List<Country>,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<Language>
): MediaTypeItem(MediaType.MOVIE) {

    @JsonClass(generateAdapter = true)
    data class Slim(
        val id: Int,
        val title: String,
        @Json(name = "backdrop_path")
        @ImageAnnotation
        val backdrop: Image?,
        @Json(name = "poster_path")
        @ImageAnnotation
        val poster: Image?,
        @Json(name = "original_language")
        val originalLaguage: String,
        @Json(name = "genre_ids")
        val genreIds: List<Int>,
        @Json(name = "vote_average")
        val voteAverage: Float,
        val overview: String,
        @Json(name = "release_date")
        val releaseDate: Date?,
        val adult: Boolean,
        val popularity: Double,
        @Json(name = "vote_count")
        val voteCount: Int,
        val video: Boolean,

        @Rated
        val rating: Int?
    ): MediaTypeItem(MediaType.MOVIE)
}