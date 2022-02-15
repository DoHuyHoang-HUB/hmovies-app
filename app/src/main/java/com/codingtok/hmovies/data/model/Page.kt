package com.codingtok.hmovies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page<T>(
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    val results: List<T>
) {
    val hasNextPage: Boolean = page < totalPages
}