package com.codingtok.hmovies.ui.home

import android.content.res.Resources
import androidx.lifecycle.*
import com.codingtok.hmovies.POPULAR
import com.codingtok.hmovies.R
import com.codingtok.hmovies.TOP_RATED
import com.codingtok.hmovies.TRENDING
import com.codingtok.hmovies.data.network.service.trending.Trending
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.data.repository.GenreRepository
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository,
    private val genreRepository: GenreRepository,
    private val resource: Resources
) :BaseRefreshViewModel<Issue<*>>() {

    override fun loadData(page: Int, param: Any?) {
        viewModelScope.launch {
            val newItemList = arrayListOf<Issue<*>>()
            movieRepository.getNowPlaying(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body.results, null, Issue.LayoutType.SLIDER_LAYOUT))
                    }
                    else ->  onError(response)
                }
            }
            trendingRepository.getMovieTrending(Trending.TimeWindow.WEEK).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body.results, resource.getString(R.string.new_movie_trending_on_hmovie), Issue.LayoutType.RECYCLER_LAYOUT, TRENDING))
                    }
                    else -> onError(response)
                }
            }
            movieRepository.getTopRated(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body.results, resource.getString(R.string.top_5_movie_today), Issue.LayoutType.RECYCLER_LAYOUT, TOP_RATED))
                    }
                    else -> onError(response)
                }
            }
            movieRepository.getPopular(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body.results, resource.getString(R.string.popular_movie), Issue.LayoutType.RECYCLER_LAYOUT, POPULAR))
                    }
                    else -> onError(response)
                }
            }
            genreRepository.getMovieList(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body, resource.getString(R.string.discover), Issue.LayoutType.RECYCLER_DISCOVER_LAYOUT))
                    }
                    else -> onError(response)
                }
            }
            onLoadSuccess(page, newItemList)
        }
    }
}

