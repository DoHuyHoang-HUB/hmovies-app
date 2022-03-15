package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.annotations.ImageAnnotation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Collection(
    val id: Int,
    val name: String,
    val overview: String,
    @Json(name = "parts")
    val movies: List<Movie.Slim>,
    @Json(name = "backdrop_path")
    @ImageAnnotation
    val backdrop: Image?
): Serializable {
    val movieCount: Int get() = movies.size

    @JsonClass(generateAdapter = true)
    internal data class Images(
        val backdrops: List<Image>,
        val posters: List<Image>
    )

    @JsonClass(generateAdapter = true)
    data class Slim(
        @Json(name = "id")
        val collectionId: Int,
        val name: String,
        @Json(name = "backdrop_path")
        @ImageAnnotation
        val backdrop: Image?,
        @Json(name = "poster_path")
        @ImageAnnotation
        val poster: Image?,
        val overview: String?
    ): Serializable
}
