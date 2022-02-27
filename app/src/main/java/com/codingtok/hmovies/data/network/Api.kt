package com.codingtok.hmovies.data.network

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
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIME_OUT_READ = 30 // In second
private const val CONTENT_TYPE = "Content-Type"
private const val CONTENT_TYPE_VALUE = "application/json"
private const val TIME_OUT_CONNECT = 30 // In second

@Singleton
class Api @Inject constructor() {

    private val client: OkHttpClient
    private val retrofit: Retrofit

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
            .add(MyStandardJsonAdapters.FACTORY)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor;
        }

    init {
        client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val httpUrl = original.url.newBuilder()
                    .addQueryParameter(QUERY_KEY_API, KEY_API)
                val builder = original.newBuilder()
                    .url(httpUrl.build())
                    .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                chain.proceed(builder.build())
            }
            .addInterceptor(logger)
            .connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}
