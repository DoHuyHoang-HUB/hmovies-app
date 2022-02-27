package com.codingtok.hmovies.data.network.helper.impl

import com.codingtok.hmovies.BuildConfig
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.Api
import com.codingtok.hmovies.data.network.helper.DiscoverHelper
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.data.network.service.discover.DiscoverService
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class DiscoverHelperImpl @Inject
constructor(private val api: Api): DiscoverHelper {

    override suspend fun getMoviesDiscover(
        options: Discover.MovieBuilder,
        languageTag: String?,
        page: Int
    ): NetworkResponse<Page<Movie.Slim>, Error.DefaultError> {
        val discoverService: DiscoverService by lazy {
            api.createService(DiscoverService::class.java)
        }
        return discoverService.getMoviesDiscover(options, languageTag, page)
    }
}