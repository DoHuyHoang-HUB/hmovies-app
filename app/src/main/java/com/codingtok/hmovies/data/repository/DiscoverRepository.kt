package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {
    suspend fun getMoviesDiscover(
        options: Discover.MovieBuilder,
        languageTag: String? = null,
        page: Int = 1
    ): Flow<Resource<Page<Movie.Slim>>>
}