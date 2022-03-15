package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.internals.annotations.ImageAnnotation
import com.codingtok.hmovies.data.enums.MediaType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Person(
    val name: String,
    val id: Int,
    val gender: PersonGender,
    val biography: String,
    @Json(name = "deathday")
    val deathDay: Date?,
    @Json(name = "place_of_birth")
    val birthDay: Date?,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "profile_path")
    @ImageAnnotation
    val profile: Image?,
    @Json(name = "images")
    internal val _profiles: List<Image>?
): MediaTypeItem(MediaType.PERSON), Serializable {
    val profiles: List<Image> = _profiles ?: emptyList()

    val isDead: Boolean = deathDay != null

    @JsonClass(generateAdapter = true)
    data class Slim(
        val adult: Boolean,
        val name: String,
        val id: Int,
        val gender: PersonGender,
        @Json(name = "profile_path")
        @ImageAnnotation
        val profile: Image?,
        val popularity: Double,
        @Json(name = "known_for_department")
        val knownForDepartment: String,
        @Json(name = "original_name")
        val originalName: String?
    ): MediaTypeItem(MediaType.PERSON), Serializable

    data class CrewJob(
        val creditId: String,
        val job: String,
        val department: String,
        val episodeCount: Int? = null
    )

    data class CastRole(
        val creditId: String,
        val character: String,
        val order: Int = -1,
        val episodeCount: Int? = null
    )
}