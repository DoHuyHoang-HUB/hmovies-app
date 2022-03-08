package com.codingtok.hmovies.data.network.service

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Genres
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getMovieList(): NetworkResponse<List<Genres>, Error.DefaultError>;
}