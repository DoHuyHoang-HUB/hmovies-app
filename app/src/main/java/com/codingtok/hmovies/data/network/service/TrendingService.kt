package com.codingtok.hmovies.data.network.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingService {
    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") mediaType: Trending.Type,
        @Path("time_window") timeWindow: Trending.TimeWindow
    ): NetworkResponse<Page<MediaTypeItem>, Error.DefaultError>
}