package com.codingtok.hmovies.data.network.service.trending

import com.codingtok.hmovies.data.model.*
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {
    @GET("trending/all/{time_window}")
    suspend fun getAllTrending(
        @Path("time_window") timeWindow: Trending.TimeWindow,
        @Query("page") page: Int? = 1
    ): NetworkResponse<Page<MediaTypeItem>, Error.DefaultError>

    @GET("trending/movie/{time_window}")
    suspend fun getMovieTrending(
        @Path("time_window") timeWindow: Trending.TimeWindow,
        @Query("page") page: Int? = 1
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError>

    @GET("trending/tv/{time_window}")
    suspend fun getTVShowTrending(
        @Path("time_window") timeWindow: Trending.TimeWindow,
        @Query("page") page: Int? = 1
    ): NetworkResponse<Page<TVShow.Slim>, Error.DefaultError>

    @GET("trending/person/{time_window}")
    suspend fun getPersonTrending(
        @Path("time_window") timeWindow: Trending.TimeWindow,
        @Query("page") page: Int? = 1
    ): NetworkResponse<Page<Person.Slim>, Error.DefaultError>

}