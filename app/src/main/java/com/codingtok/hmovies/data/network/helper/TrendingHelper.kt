package com.codingtok.hmovies.data.network.helper

import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingHelper {
    suspend fun get(
        mediaType: Trending.Type,
        timeWindow: Trending.TimeWindow
    ): Resource<Page<MediaTypeItem>>
}