package com.codingtok.hmovies.ui.home

import androidx.lifecycle.*
import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.codingtok.hmovies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository,
) :BaseViewModel() {
    private val _nowPlayingMovie = MutableLiveData<Resource<Page<Movie.Slim>>>()
    val nowPlayingMovie: LiveData<Resource<Page<Movie.Slim>>> get() = _nowPlayingMovie

    private val _trendingMovie = MutableLiveData<Resource<Page<MediaTypeItem>>>()
    val trendingMovie: LiveData<Resource<Page<MediaTypeItem>>> get() = _trendingMovie

    private val _topRatedMovie = MutableLiveData<Resource<Page<Movie.Slim>>>()
    val topRatedMovie: LiveData<Resource<Page<Movie.Slim>>> get() = _topRatedMovie

    private val _popularMovie = MutableLiveData<Resource<Page<Movie.Slim>>>()
    val popularMovie: LiveData<Resource<Page<Movie.Slim>>> get() = _popularMovie

    init {
        getNowPlayingMovie()
        getTrendingMovie()
        getTopRatedMovie()
        getPopularMovie()
    }

    private fun getNowPlayingMovie() {
        viewModelScope.launch() {
            _nowPlayingMovie.value = Resource.loading()
            movieRepository.getNowPlaying(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _nowPlayingMovie.value = it
            }
        }
    }

    private fun getTrendingMovie() {
        viewModelScope.launch() {
            _trendingMovie.value = Resource.loading()
            trendingRepository.get(
                Trending.Type.MOVIE,
                Trending.TimeWindow.WEEK
            ).collect {
                _trendingMovie.value = it
            }
        }
    }

    private fun getTopRatedMovie() {
        viewModelScope.launch() {
            _topRatedMovie.value = Resource.loading()
            movieRepository.getTopRated(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _topRatedMovie.value = it
            }
        }
    }

    private fun getPopularMovie() {
        viewModelScope.launch() {
            _popularMovie.value = Resource.loading()
            movieRepository.getPopular(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _popularMovie.value = it
            }
        }
    }
}

