package com.codingtok.hmovies.data.internals.models

import com.codingtok.hmovies.data.internals.annotations.OtherCases
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Person
import com.codingtok.hmovies.data.model.TVShow
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
internal data class FindResult internal constructor(
    @Json(name = "movie_results")
    val movies: List<Movie.Slim>,

    @Json(name = "tv_results")
    val shows: List<TVShow.Slim>,

    @Json(name = "person_results")
    val persons: List<Person.Slim>,
) {
    companion object {
        internal val ADAPTER = object : Any() {
            @OtherCases
            @FromJson
            fun from(findResult: FindResult?): MediaTypeItem? {
                if (findResult == null) return null
                return when {
                    findResult.movies.isNotEmpty() -> {
                        findResult.movies.first()
                    }
                    findResult.shows.isNotEmpty() -> {
                        findResult.shows.first()
                    }
                    findResult.persons.isNotEmpty() -> {
                        findResult.persons.first()
                    }
                    else -> null
                }
            }

            @ToJson
            fun to(@OtherCases mediaTypeItem: MediaTypeItem): FindResult {
                throw UnsupportedOperationException()
            }
        }
    }
}