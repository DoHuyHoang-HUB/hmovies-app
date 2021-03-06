package com.codingtok.hmovies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "iso_3166_1")
    val countryCode: String,
    val name: String
): Serializable