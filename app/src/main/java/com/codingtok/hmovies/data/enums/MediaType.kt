package com.codingtok.hmovies.data.enums

import com.squareup.moshi.Json

enum class MediaType {
    @Json(name = "tv")
    TV,
    @Json(name = "movie")
    MOVIE,
    UNKNOWN,
}