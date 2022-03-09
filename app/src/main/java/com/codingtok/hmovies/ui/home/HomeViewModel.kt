package com.codingtok.hmovies.ui.home

import android.content.res.Resources
import androidx.lifecycle.*
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository,
    private val resource: Resources
) :BaseRefreshViewModel<Issue<Movie.Slim>>() {

    override fun loadData(page: Int) {
        viewModelScope.launch {
            val newItemList = arrayListOf<Issue<Movie.Slim>>()
            movieRepository.getNowPlaying(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body, null, Issue.LayoutType.SLIDER_LAYOUT))
                    }
                    else ->  onError(response)
                }
            }
            trendingRepository.getTrending(Trending.Type.MOVIE, Trending.TimeWindow.WEEK).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body as Page<Movie.Slim>, resource.getString(R.string.new_movie_trending_on_hmovie), Issue.LayoutType.RECYCLER_LAYOUT))
                    }
                    else -> onError(response)
                }
            }
            movieRepository.getTopRated(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body, resource.getString(R.string.top_5_movie_today), Issue.LayoutType.RECYCLER_LAYOUT))
                    }
                    else -> onError(response)
                }
            }
            movieRepository.getPopular(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        newItemList.add(Issue(response.body, resource.getString(R.string.popular_movie), Issue.LayoutType.RECYCLER_LAYOUT))
                    }
                    else -> onError(response)
                }
            }
            onLoadSuccess(newItemList)
        }
    }

}

