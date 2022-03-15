package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.internals.annotations.ImageAnnotation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WatchProvider internal constructor(
    @Json(name = "logo_path")
    @ImageAnnotation
    val logo: Image?,
    @Json(name = "provider_name")
    val name: String,
    @Json(name = "provider_id")
    val id: Int,
    @Json(name = "display_priority")
    val displayPriority: Int
)