package com.codingtok.hmovies.data.network.helper

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.utils.Resource

interface MovieHelper {

    suspend fun getPopular(
        languageTag: String?,
        page: Int = 1,
        languageCode: String? = null
    ): Resource<Page<Movie.Slim>>

    suspend fun getNowPlaying(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Resource<Page<Movie.Slim>>

    suspend fun getTopRated(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Resource<Page<Movie.Slim>>
}