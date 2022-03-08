package com.codingtok.hmovies.ui.home

import androidx.lifecycle.*
import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.codingtok.hmovies.utils.SingleMutableLiveData
import com.haroldadmin.cnradapter.invoke
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
    private val discoverRepository: DiscoverRepository
) :BaseViewModel() {
    private val _nowPlayingMovie = SingleMutableLiveData<Page<Movie.Slim>>()
    val nowPlayingMovie: LiveData<Page<Movie.Slim>> get() = _nowPlayingMovie

    private val _trendingMovie = SingleMutableLiveData<Page<MediaTypeItem>>()
    val trendingMovie: LiveData<Page<MediaTypeItem>> get() = _trendingMovie

    private val _topRatedMovie = SingleMutableLiveData<Page<Movie.Slim>>()
    val topRatedMovie: LiveData<Page<Movie.Slim>> get() = _topRatedMovie

    private val _popularMovie = SingleMutableLiveData<Page<Movie.Slim>>()
    val popularMovie: LiveData<Page<Movie.Slim>> get() = _popularMovie

    private val _discoverMovie = SingleMutableLiveData<Page<Movie.Slim>>()
    val discoverMovie: LiveData<Page<Movie.Slim>> get() = _discoverMovie
    init {
        getNowPlayingMovie()
        getTrendingMovie()
        getTopRatedMovie()
        getPopularMovie()
    }

    private fun getNowPlayingMovie() {
        viewModelScope.launch {
            movieRepository.getNowPlaying(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _nowPlayingMovie.value = it.invoke()
            }
        }
    }

    private fun getTrendingMovie() {
        viewModelScope.launch {
            trendingRepository.get(
                Trending.Type.MOVIE,
                Trending.TimeWindow.WEEK
            ).collect {
                _trendingMovie.value = it.invoke()
            }
        }
    }

    private fun getTopRatedMovie() {
        viewModelScope.launch {
            movieRepository.getTopRated(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _topRatedMovie.value = it.invoke()
            }
        }
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            movieRepository.getPopular(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _popularMovie.value = it.invoke()
            }
        }
    }

    private fun getDiscoverMovie() {
        viewModelScope.launch {
            val options = Discover.MovieBuilder()
                .sortBy(Discover.SortBy.POPULARITY_DESC)
        }
    }
}

