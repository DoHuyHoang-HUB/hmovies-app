package com.codingtok.hmovies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Language(
    @Json(name = "iso_639_1")
    val languageCode: String,
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "name")
    val nativeName: String
): Serializable