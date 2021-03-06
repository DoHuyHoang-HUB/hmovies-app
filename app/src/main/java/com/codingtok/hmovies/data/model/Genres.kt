package com.codingtok.hmovies.data.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Genres(
    val id: Int,
    val name: String
): Serializable