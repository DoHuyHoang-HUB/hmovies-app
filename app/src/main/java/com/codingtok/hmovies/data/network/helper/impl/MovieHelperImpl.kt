package com.codingtok.hmovies.data.network.helper.impl

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.MovieHelper
import com.codingtok.hmovies.data.network.service.MovieService
import com.codingtok.hmovies.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class MovieHelperImpl @Inject
constructor(private val movieService: MovieService): MovieHelper {
    override suspend fun getPopular(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Resource<Page<Movie.Slim>> {
        return when(val movieResponse = movieService.getPopular(languageTag, page, languageCode)) {
            is NetworkResponse.Success -> {
                Resource.success(movieResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(movieResponse.error.message)
            }
            is NetworkResponse.ServerError -> {
                Resource.error(movieResponse.error.message, movieResponse.body)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(movieResponse.error.message)
            }
        }
    }


    override suspend fun getNowPlaying(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Resource<Page<Movie.Slim>> {
        return when (val movieResponse = movieService.getNowPlaying(languageTag, page, languageCode)) {
            is NetworkResponse.Success -> {
                Resource.success(movieResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(movieResponse.error.message)
            }
            is NetworkResponse.ServerError -> {
                Resource.error(movieResponse.error.message, movieResponse.body)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(movieResponse.error.message)
            }
        }
    }

    override suspend fun getTopRated(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Resource<Page<Movie.Slim>> {
        return when (val movieResponse = movieService.getTopRated(languageTag, page, languageCode)) {
            is NetworkResponse.Success -> {
                Resource.success(movieResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(movieResponse.error.message)
            }
            is NetworkResponse.ServerError -> {
                Resource.error(movieResponse.error.message, movieResponse.body)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(movieResponse.error.message)
            }
        }
    }

    override suspend fun getLatest(languageTag: String?): Resource<Page<Movie.Slim>> {
        return when (val movieResponse = movieService.getLatest(languageTag)) {
            is NetworkResponse.Success -> {
                Resource.success(movieResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(movieResponse.error.message)
            }
            is NetworkResponse.ServerError -> {
                Resource.error(movieResponse.error.message, movieResponse.body)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(movieResponse.error.message)
            }
        }
    }
}