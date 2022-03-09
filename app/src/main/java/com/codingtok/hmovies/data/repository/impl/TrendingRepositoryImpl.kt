package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.service.TrendingService
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
    override suspend fun get(
        mediaType: Trending.Type,
        timeWindow: Trending.TimeWindow
    ): Flow<NetworkResponse<Page<MediaTypeItem>, Error.DefaultError>> {
        return flow {
            emit(trendingService.get(mediaType, timeWindow))
        }.flowOn(ioDispatcher)
    }
}