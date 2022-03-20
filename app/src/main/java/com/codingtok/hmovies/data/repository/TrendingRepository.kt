package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.model.*
import com.codingtok.hmovies.data.network.service.trending.Trending
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingRepository {
    suspend fun getAllTrending(
        timeWindow: Trending.TimeWindow,
        page: Int? = 1
    ): Flow<NetworkResponse<Page<MediaTypeItem>, Error.DefaultError>>

    suspend fun getMovieTrending(
        timeWindow: Trending.TimeWindow,
        page: Int? = 1
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>>

    suspend fun getTVShowTrending(
        timeWindow: Trending.TimeWindow,
        page: Int? = 1
    ): Flow<NetworkResponse<Page<TVShow.Slim>, Error.DefaultError>>

    suspend fun getPersonTrending(
        timeWindow: Trending.TimeWindow,
        page: Int? = 1
    ): Flow<NetworkResponse<Page<Person.Slim>, Error.DefaultError>>
}