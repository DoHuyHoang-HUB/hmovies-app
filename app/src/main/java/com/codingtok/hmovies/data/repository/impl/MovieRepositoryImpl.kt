package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.*
import com.codingtok.hmovies.data.network.service.movie.AppendToResponse
import com.codingtok.hmovies.data.network.service.movie.MovieService
import com.codingtok.hmovies.data.repository.MovieRepository
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

    override suspend fun getDetail(
        movieId: Int,
        languageTag: String?,
        append: AppendToResponse?
    ): Flow<NetworkResponse<Movie, Error.DefaultError>> {
        return flow {
            emit(movieService.getDetail(movieId, languageTag, append))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getBackdrops(
        movieId: Int,
        languageTag: String?
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>> {
        return flow {
            emit(movieService.getBackdrops(movieId, languageTag))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getPosters(
        movieId: Int,
        languageTag: String?
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>> {
        return flow {
            emit(movieService.getPosters(movieId, languageTag))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getLogos(
        movieId: Int,
        languageTag: String?
    ): Flow<NetworkResponse<List<Image>, Error.DefaultError>> {
        return flow {
            emit(movieService.getLogos(movieId, languageTag))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCreditsCast(
        movieId: Int,
        languageTag: String?
    ): Flow<NetworkResponse<List<Pair<Person.Slim, Person.CastRole>>, Error.DefaultError>> {
        return flow {
            emit(movieService.getCreditsCast(movieId, languageTag))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCreditsCrew(
        movieId: Int,
        languageTag: String?
    ): Flow<NetworkResponse<List<Pair<Person.Slim, Person.CrewJob>>, Error.DefaultError>> {
        return flow {
            emit(movieService.getCreditsCrew(movieId, languageTag))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecommendations(
        movieId: Int,
        languageTag: String?,
        page: Int?
    ): Flow<NetworkResponse<Page<Movie.Slim>, Error.DefaultError>> {
        return flow {
            emit(movieService.getRecommendations(movieId, languageTag, page))
        }.flowOn(ioDispatcher)
    }
}