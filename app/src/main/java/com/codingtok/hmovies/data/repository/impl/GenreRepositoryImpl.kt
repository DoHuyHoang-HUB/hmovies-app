package com.codingtok.hmovies.data.repository.impl

import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.network.service.GenreService
import com.codingtok.hmovies.data.repository.GenreRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GenreRepositoryImpl @Inject
constructor(
    private val genreService: GenreService,
    private val ioDispatcher: CoroutineContext
): GenreRepository{
    override suspend fun getMovieList(languageTag: String?): Flow<NetworkResponse<List<Genres>, Error.DefaultError>> {
        return flow {
            emit(genreService.getMovieList(languageTag))
        }.flowOn(ioDispatcher)
    }
}