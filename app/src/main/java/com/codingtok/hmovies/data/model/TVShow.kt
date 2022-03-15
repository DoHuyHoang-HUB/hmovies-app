package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.annotations.ImageAnnotation
import com.codingtok.hmovies.data.annotations.Rated
import com.codingtok.hmovies.data.enums.MediaType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class TVShow(
    val id: String,
    @Json(name = "original_name")
    val originalName: String? = null,
    val name: String? = null,
    val popularity: Double? = null,
    @Json(name = "vote_count")
    val voteCount: Int? = null,
    @Json(name = "first_air_date")
    val firstAirDate: String? = null,
    @Json(name = "backdrop_path")
    @ImageAnnotation
    val backdropPath: Image? = null,
    @Json(name = "original_language")
    val originalLanguage: String? = null,
    @Json(name = "vote_average")
    val voteAverage: Double? = null,
    val overview: String? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null
): MediaTypeItem(MediaType.TV), Serializable {
    @JsonClass(generateAdapter = true)
    data class Slim internal constructor(
        val id: Int,
        @Json(name = "name")
        val title: String,
        @Json(name = "first_air_date")
        val firstAirDate: Image?,
        @Json(name = "backdrop_path")
        @ImageAnnotation
        val backdrop: Image?,
        @Json(name = "poster_path")
        @ImageAnnotation
        val poster: Image?,
        @Json(name = "original_name")
        val originalTitle: String,
        @Json(name = "genre_ids")
        val genreIds: List<Int>,
        @Json(name = "vote_average")
        val voteAverage: Double,
        @Json(name = "vote_count")
        val voteCount: Int,
        val popularity: Double,

        // Optional when getting rated shows (AccountService)
        @Rated
        val rating: Int?
    ) : MediaTypeItem(MediaType.TV), Serializable
}
