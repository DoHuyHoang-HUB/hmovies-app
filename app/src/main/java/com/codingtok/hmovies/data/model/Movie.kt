package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.internals.annotations.ImageAnnotation
import com.codingtok.hmovies.data.internals.annotations.Rated
import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.enums.ProductionStatus
import com.codingtok.hmovies.data.internals.annotations.OtherCases
import com.codingtok.hmovies.data.internals.annotations.ResultsList
import com.codingtok.hmovies.data.internals.models.Images
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

data class Movie internal constructor(
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
    val spokenLanguages: List<Language>,

    // Append
    @Json(name = "images")
    internal val _images: Images?,
    @Json(name = "recommendations")
    internal val _recommendations: Page<Slim>?,
    @Json(name = "similar")
    internal val _similar: Page<Slim>?,
    @Json(name = "external_ids")
    val externalIds: ExternalIds?,
    @ResultsList
    @Json(name = "videos")
    internal val _videos: List<Video>?,
    @Json(name = "keywords")
    @ResultsList("keywords")
    internal val _keywords: List<Keyword>?,
    @Json(name = "watch/providers")
    @OtherCases
    val watchProviders: Map<String, WatchProviderListObject>?
): MediaTypeItem(MediaType.MOVIE), Serializable {

    val videos: List<Video> = _videos ?: emptyList()
    val backdrops: List<Image> = _images?.backdrops ?: emptyList()
    val posters: List<Image> = _images?.posters ?: emptyList()
    val logos: List<Image> = _images?.logos ?: emptyList()

    val recommendations: List<Slim> = _recommendations?.results ?: emptyList()
    val similar: List<Slim> = _similar?.results ?: emptyList()

    val keyword: List<Keyword> = _keywords ?: emptyList()

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
    ): MediaTypeItem(MediaType.MOVIE), Serializable
}