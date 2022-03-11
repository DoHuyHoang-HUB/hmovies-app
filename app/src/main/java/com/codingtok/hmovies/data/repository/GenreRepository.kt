package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Genres
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getMovieList(languageTag: String? = null): Flow<NetworkResponse<List<Genres>, Error.DefaultError>>;
}