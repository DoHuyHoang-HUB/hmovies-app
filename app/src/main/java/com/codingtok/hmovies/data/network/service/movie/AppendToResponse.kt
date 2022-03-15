package com.codingtok.hmovies.data.network.service.movie

import java.util.*

class AppendToResponse(private vararg val appendItems: Item = arrayOf()) {

    private val seasons = mutableListOf<Int>()

    override fun toString(): String {
        var result = ""
        result += appendItems.joinToString(separator = "," ) { it.toString() }
        if (seasons.isNotEmpty()) {
            result += if (appendItems.isNotEmpty()) "," else ""
            result += seasons.joinToString(separator = ",") { "season/$it"}
        }
        return result
    }

    enum class Item(private val key: String) {
        IMAGES(""), VIDEOS(""), EXTERNAL_IDS(""),
        RECOMMENDATIONS(""), SIMILAR(""), KEYWORDS(""),
        WATCH_PROVIDERS("watch/provides");

        override fun toString(): String = key.ifBlank { name.lowercase(Locale.ROOT) }
    }
}