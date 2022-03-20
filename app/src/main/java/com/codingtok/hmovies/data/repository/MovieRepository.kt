package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.internals.annotations.ResultsList
import com.codingtok.hmovies.data.model.*
import com.codingtok.hmovies.data.network.service.movie.AppendToResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    suspend fun getMovies(
        type: String,
        languageTag: String? = null,
        page: Int = 1,
        languageCode: String? = null
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>

    suspend fun getDetail(
        movieId: Int,
        languageTag: String? = null,
        append: AppendToResponse? = null
    ): Flow<NetworkResponse<Movie, Error.DefaultError>>

    suspend fun getBackdrops(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>>

    suspend fun getPosters(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>>

    suspend fun getLogos(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>>

    suspend fun getCreditsCast(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Pair<Person.Slim, Person.CastRole>>, Error.DefaultError>>

    suspend fun getCreditsCrew(
        movieId: Int,
        languageTag: String? = null
    ): Flow<NetworkResponse<List<Pair<Person.Slim, Person.CrewJob>>, Error.DefaultError>>

    suspend fun getRecommendations(
        movieId: Int,
        languageTag: String? = null,
        page: Int? = null
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>
}