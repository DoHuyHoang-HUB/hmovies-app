package com.codingtok.hmovies.data.network.api

import com.codingtok.hmovies.data.annotations.ImageAdapter
import com.codingtok.hmovies.data.annotations.RatedJsonAdapter
import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.model.Date
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.TVShow
import com.codingtok.hmovies.data.network.service.MovieService
import com.codingtok.hmovies.data.network.service.TrendingService
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private val moshi: Moshi by lazy {
    return@lazy Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(MediaTypeItem::class.java, "media_type")
                .withSubtype(Movie.Slim::class.java, "movie")
                .withSubtype(TVShow.Slim::class.java, "tv")
        )
        .add(
            MediaType::class.java, EnumJsonAdapter.create(MediaType::class.java)
                .withUnknownFallback(MediaType.UNKNOWN)
        )
        .add(Date.ADAPTER)
        .add(ImageAdapter.INSTANCE)
        .add(RatedJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
}

private val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter("api_key", ApiConfig.KEY_API)
        val builder = original.newBuilder()
            .url(httpUrl.build())
            .addHeader("Content-Type", "application/json")
        chain.proceed(builder.build())
    }
    .build()

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(ApiConfig.BASE_URL)
    .client(client)
    .build()

object Api {
    val trendingService: TrendingService by lazy {
        retrofit.create(TrendingService::class.java)
    }
    val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}