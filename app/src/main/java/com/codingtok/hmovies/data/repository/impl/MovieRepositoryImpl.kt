package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.impl.MovieHelperImpl
import com.codingtok.hmovies.data.network.service.MovieService
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl @Inject
constructor(
    private val movieService: MovieService,
    private val ioDispatcher: CoroutineContext
): MovieRepository{
    override suspend fun getPopular(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(movieService.getPopular(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getNowPlaying(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(movieService.getNowPlaying(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTopRated(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(movieService.getTopRated(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }
}