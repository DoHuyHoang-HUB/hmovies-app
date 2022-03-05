package com.codingtok.hmovies.data.network.helper.impl

import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.TrendingHelper
import com.codingtok.hmovies.data.network.service.TrendingService
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class TrendingHelperImpl @Inject
constructor(private val trendingService: TrendingService): TrendingHelper{
    override suspend fun get(
        mediaType: Trending.Type,
        timeWindow: Trending.TimeWindow
    ): Resource<Page<MediaTypeItem>> {
        return when(val trendingResponse = trendingService.get(mediaType, timeWindow)) {
            is NetworkResponse.Success -> {
                Resource.success(trendingResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(trendingResponse.error.message)
            }
            is NetworkResponse.ServerError -> {
                Resource.error(trendingResponse.error.message, trendingResponse.body)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(trendingResponse.error.message)
            }
        }
    }
}