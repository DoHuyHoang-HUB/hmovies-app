package com.codingtok.hmovies.data.network.service

import com.codingtok.hmovies.data.internals.annotations.ResultsList
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Genres
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    @ResultsList("genres")
    suspend fun getMovieList(
        @Query("language") languageTag: String? = null
    ): NetworkResponse<List<Genres>, Error.DefaultError>
}