package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.annotations.ImageAnnotation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
    val id: Int,
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String,
    @Json(name = "logo_path")
    @ImageAnnotation
    val logo: Image?,

    val headquarters: String?,
    val homepage: String?,
    val description: String?
) {
}