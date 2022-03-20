package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.*
import com.codingtok.hmovies.data.network.service.trending.Trending
import com.codingtok.hmovies.data.network.service.trending.TrendingService
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TrendingRepositoryImpl @Inject
constructor(
    private val trendingService: TrendingService,
    private val ioDispatcher: CoroutineContext
): TrendingRepository {
    override suspend fun getAllTrending(
        timeWindow: Trending.TimeWindow,
        page: Int?
    ): Flow<NetworkResponse<Page<MediaTypeItem>, Error.DefaultError>> {
        return flow {
            emit(trendingService.getAllTrending(timeWindow, page))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getMovieTrending(
        timeWindow: Trending.TimeWindow,
        page: Int?
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(trendingService.getMovieTrending(timeWindow, page))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTVShowTrending(
        timeWindow: Trending.TimeWindow,
        page: Int?
    ): Flow<NetworkResponse<Page<TVShow.Slim>, Error.DefaultError>> {
        return flow {
            emit(trendingService.getTVShowTrending(timeWindow, page))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getPersonTrending(
        timeWindow: Trending.TimeWindow,
        page: Int?
    ): Flow<NetworkResponse<Page<Person.Slim>, Error.DefaultError>> {
        return flow {
            emit(trendingService.getPersonTrending(timeWindow, page))
        }.flowOn(ioDispatcher)
    }
}