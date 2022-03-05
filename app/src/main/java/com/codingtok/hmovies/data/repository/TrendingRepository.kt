package com.codingtok.hmovies.data.repository

import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    suspend fun get(
        mediaType: Trending.Type,
        timeWindow: Trending.TimeWindow
    ): Flow<Resource<Page<MediaTypeItem>>>
}