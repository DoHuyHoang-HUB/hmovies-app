package com.codingtok.hmovies.data.model

import com.codingtok.hmovies.data.model.WatchProvider
import com.squareup.moshi.JsonClass

@Suppress("SpellCheckingInspection")
@JsonClass(generateAdapter = true)
data class WatchProviderListObject internal constructor(
    val link: String,
    val flatrate: List<WatchProvider>?,
    val buy: List<WatchProvider>?,
    val ads: List<WatchProvider>?,
    val free: List<WatchProvider>?,
    val rent: List<WatchProvider>?
)