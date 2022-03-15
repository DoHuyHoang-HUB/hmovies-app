package com.codingtok.hmovies.data.network.service.movie

import com.codingtok.hmovies.data.internals.annotations.CharJob
import com.codingtok.hmovies.data.internals.annotations.ResultsList
import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.model.*
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

    @GET("movie/{movie_id}/credits")
    @CharJob(fieldName = "cast", mediaType = MediaType.PERSON)
    suspend fun getCreditsCast(
        @Path("movie_id") movieId: Int,
        @Query("language") languageTag: String? = null
    ): NetworkResponse<List<Pair<Person.Slim, Person.CrewJob>>, Error.DefaultError>

    @GET("movie/{movie_id}/credits")
    @CharJob(fieldName = "crew", mediaType = MediaType.PERSON)
    suspend fun getCreditsCrew(
        @Path("movie_id") movieId: Int,
        @Query("language") languageTag: String? = null
    ): NetworkResponse<List<Pair<Person.Slim, Person.CrewJob>>, Error.DefaultError>
}