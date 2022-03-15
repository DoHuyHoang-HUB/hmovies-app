package com.codingtok.hmovies.data.network.service.movie

import com.codingtok.hmovies.data.annotations.ResultsList
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Image
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("language") languageTag: String? = null,
        @Query("page") page: Int = 1,
        @Query("region") languageCode: String? = null
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") languageTag: String? = null,
        @Query("append_to_response") append: AppendToResponse? = null
    ): NetworkResponse<Movie, Error.DefaultError>

    @GET("movie/{movie_id}/images")
    @ResultsList("backdrops")
    suspend fun getBackdrops(
        @Path("movie_id") movieId: Int,
        @Query("language") languageTag: String? = null
    ): NetworkResponse<List<Image>, Error.DefaultError>

    @GET("movie/{movie_id}/images")
    @ResultsList("posters")
    suspend fun getPosters(
        @Path("movie_id") movieId: Int,
        @Query("language") languageTag: String? = null
    ): NetworkResponse<List<Image>, Error.DefaultError>

}