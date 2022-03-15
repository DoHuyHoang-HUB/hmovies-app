package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.model.Person
import com.codingtok.hmovies.data.network.service.movie.AppendToResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopular(
        languageTag: String?,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>

    suspend fun getNowPlaying(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>

    suspend fun getTopRated(
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>

    suspend fun getDetail(
        movieId: Int,
        languageTag: String? = null,
        append: AppendToResponse? = null
    ): Flow<NetworkResponse<Movie, Error.DefaultError>>

    suspend fun getCreditsCast(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Pair<Person.Slim, Person.CrewJob>>, Error.DefaultError>>
}