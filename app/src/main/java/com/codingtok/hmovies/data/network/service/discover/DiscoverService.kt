package com.codingtok.hmovies.data.network.service.discover

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface DiscoverService {
    /**
     * Reference: [The Movie Database API](https://developers.themoviedb.org/3/discover/movie)
     */
    @GET("discover/movie")
    suspend fun getMoviesDiscover(
        @QueryMap options: Discover.MovieBuilder,
        @Query("language") languageTag: String? = null,
        @Query("page") page: Int = 1
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError>
}