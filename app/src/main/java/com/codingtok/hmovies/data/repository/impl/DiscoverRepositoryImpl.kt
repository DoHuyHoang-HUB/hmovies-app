package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.data.network.service.discover.DiscoverService
import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DiscoverRepositoryImpl @Inject constructor(
    private val discoverService: DiscoverService,
    private val ioDispatcher: CoroutineContext
): DiscoverRepository {
    override suspend fun getMoviesDiscover(
        options: Discover.MovieBuilder,
        languageTag: String?,
        page: Int
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(discoverService.getMoviesDiscover(options, languageTag, page))
        }.flowOn(ioDispatcher)
    }
}