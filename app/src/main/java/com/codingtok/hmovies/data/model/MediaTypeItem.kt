package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.enums.MediaType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
open class MediaTypeItem(
    @Json(name = "media_type")
    val mediaType: MediaType
): Serializable
