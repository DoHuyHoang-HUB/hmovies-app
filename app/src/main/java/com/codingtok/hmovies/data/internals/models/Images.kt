package com.codingtok.hmovies.data.internals.models

import com.codingtok.hmovies.data.model.Image
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class Images internal constructor(
    val posters: List<Image>?,
    val backdrops: List<Image>?,
    val logos: List<Image>?
)