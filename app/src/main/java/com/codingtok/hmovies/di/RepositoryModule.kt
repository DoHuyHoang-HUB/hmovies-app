package com.codingtok.hmovies.di

import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.data.repository.impl.DiscoverRepositoryImpl
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
    fun provideUserRepository(discoverRepositoryImpl: DiscoverRepositoryImpl): DiscoverRepository = discoverRepositoryImpl
}