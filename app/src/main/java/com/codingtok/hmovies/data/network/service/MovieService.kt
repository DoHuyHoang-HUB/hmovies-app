package com.codingtok.hmovies.data.network.service

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("language") languageTag: String? = null,
        @Query("page") page: Int = 1,
        @Query("region") languageCode: String? = null
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") languageTag: String? = null,
        @Query("page") page: Int = 1,
        @Query("region") languageCode: String? = null
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError>
}