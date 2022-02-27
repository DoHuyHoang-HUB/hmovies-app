package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.DiscoverHelper
import com.codingtok.hmovies.data.network.helper.impl.DiscoverHelperImpl
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DiscoverRepositoryImpl @Inject constructor(
    private val discoverHelper: DiscoverHelperImpl,
    private val ioDispatcher: CoroutineContext
): DiscoverRepository {
    override suspend fun getMoviesDiscover(
        options: Discover.MovieBuilder,
        languageTag: String?,
        page: Int
    ): Flow<Resource<Page<Movie.Slim>>> {
        return flow {
            emit(discoverHelper.getMoviesDiscover(options, languageTag, page))
        }.flowOn(ioDispatcher)
    }
}