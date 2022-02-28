package com.codingtok.hmovies.di

import com.codingtok.hmovies.BASE_URL
import com.codingtok.hmovies.BuildConfig
import com.codingtok.hmovies.KEY_API
import com.codingtok.hmovies.QUERY_KEY_API
import com.codingtok.hmovies.data.annotations.ImageAdapter
import com.codingtok.hmovies.data.annotations.RatedJsonAdapter
import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.model.Date
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.TVShow
import com.codingtok.hmovies.data.network.factory.MyStandardJsonAdapters
import com.codingtok.hmovies.data.network.service.discover.DiscoverService
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val TIME_OUT_READ = 10 // In second
    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"
    private val TIME_OUT_CONNECT = 10 // In second

    @Singleton
    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Singleton
    @Provides
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val original = chain.request()
            val httpUrl = original.url.newBuilder()
                .addQueryParameter(QUERY_KEY_API, KEY_API)
            val builder = original.newBuilder()
                .url(httpUrl.build())
                .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .method(original.method, original.body)
            chain.proceed(builder.build())
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("logging") logging: Interceptor,
        @Named("header") header: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(header)
            .addInterceptor(logging)
            .connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
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

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService =
        retrofit.create(DiscoverService::class.java)

}