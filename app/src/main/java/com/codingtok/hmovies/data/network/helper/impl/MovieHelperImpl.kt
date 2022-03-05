package com.codingtok.hmovies.data.network.helper.impl

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.helper.MovieHelper
import com.codingtok.hmovies.utils.Resource

class MovieHelperImpl: MovieHelper {
    override suspend fun getPopular(languageTag: String?, page: Int, languageCode: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlaying(
        languageTag: String?,
        page: Int,
        languageCode: String?
    ): Resource<Page<Movie.Slim>> {
        TODO("Not yet implemented")
    }
}