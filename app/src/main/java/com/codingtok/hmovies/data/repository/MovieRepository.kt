package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopular(
        languageTag: String?,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<Resource<Page<Movie.Slim>>>

    suspend fun getNowPlaying(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<Resource<Page<Movie.Slim>>>

    suspend fun getTopRated(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<Resource<Page<Movie.Slim>>>

    suspend fun getLatest(
        languageTag: String? = null
    ): Flow<Resource<Page<Movie.Slim>>>
}