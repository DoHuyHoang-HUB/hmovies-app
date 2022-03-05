package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.impl.TrendingHelperImpl
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TrendingRepositoryImpl @Inject
constructor(
    private val trendingHelper: TrendingHelperImpl,
    private val ioDispatcher: CoroutineContext
): TrendingRepository {
    override suspend fun get(
        mediaType: Trending.Type,
        timeWindow: Trending.TimeWindow
    ): Flow<Resource<Page<MediaTypeItem>>> {
        return flow {
            emit(trendingHelper.get(mediaType, timeWindow))
        }.flowOn(ioDispatcher)
    }
}