package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.impl.MovieHelperImpl
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl @Inject
constructor(
    private val movieHelper: MovieHelperImpl,
    private val ioDispatcher: CoroutineContext
): MovieRepository{
    override suspend fun getPopular(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<Resource<Page<Movie.Slim>>> {
        return flow {
            emit(movieHelper.getPopular(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getNowPlaying(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<Resource<Page<Movie.Slim>>> {
        return flow {
            emit(movieHelper.getNowPlaying(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTopRated(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Flow<Resource<Page<Movie.Slim>>> {
        return flow {
            emit(movieHelper.getTopRated(languageTag, page, languageCode))
        }.flowOn(ioDispatcher)
    }
}