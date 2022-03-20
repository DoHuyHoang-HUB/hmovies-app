package com.codingtok.hmovies.data.network.service.trending

import java.util.*

class Trending {
    enum class Type {
        ALL, MOVIE, TV, PERSON;
        override fun toString(): String = name.lowercase(Locale.ROOT)
    }

    enum class TimeWindow {
        DAY, WEEK;
        override fun toString(): String = name.lowercase(Locale.ROOT)
    }

}