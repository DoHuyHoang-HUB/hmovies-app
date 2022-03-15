package com.codingtok.hmovies.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Keyword internal constructor(
    val id: Int,
    val name: String
)