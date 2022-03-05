package com.codingtok.hmovies.di

import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.data.repository.impl.DiscoverRepositoryImpl
import com.codingtok.hmovies.data.repository.impl.MovieRepositoryImpl
import com.codingtok.hmovies.data.repository.impl.TrendingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDiscoverRepository(discoverRepositoryImpl: DiscoverRepositoryImpl): DiscoverRepository = discoverRepositoryImpl

    @Singleton
    @Provides
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository = movieRepositoryImpl

    @Singleton
    @Provides
    fun provideTrendingRepository(trendingRepositoryImpl: TrendingRepositoryImpl): TrendingRepository = trendingRepositoryImpl
}