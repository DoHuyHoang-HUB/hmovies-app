package com.codingtok.hmovies.ui.viewall

import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.TRENDING
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.service.trending.Trending
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.loadmorerefesh.BaseLoadMoreRefreshViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository
): BaseLoadMoreRefreshViewModel<Movie.Slim>() {

    override fun loadData(page: Int, param: Any?) {
        viewModelScope.launch {
            when (param) {
                TRENDING -> {
                    trendingRepository.getMovieTrending(
                        Trending.TimeWindow.WEEK,
                        page
                    ).collect {
                        onResponse(page, it)
                    }
                }
                else -> {
                    movieRepository.getMovies(
                        param.toString(),
                        Locale.getDefault().toLanguageTag(),
                        page
                    ).collect {
                        onResponse(page, it)
                    }
                }
            }
        }
    }

    private fun onResponse(page: Int, response: NetworkResponse<Page<Movie.Slim>, Error.DefaultError>) {
        when (response) {
            is NetworkResponse.Success -> onLoadSuccess(page, response.body.results.toMutableList())
            else -> onError(response)
        }
    }
}