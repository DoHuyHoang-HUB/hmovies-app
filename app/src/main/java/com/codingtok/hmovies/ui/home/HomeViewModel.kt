package com.codingtok.hmovies.ui.home

import androidx.lifecycle.*
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.Api
import com.codingtok.hmovies.data.network.service.discover.Discover
import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.codingtok.hmovies.utils.Resource
import com.codingtok.hmovies.utils.Status
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.invoke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {
    private val _popularMovie = MutableLiveData<Resource<Page<Movie.Slim>>>()
    val popularMovie: LiveData<Resource<Page<Movie.Slim>>> get() = _popularMovie

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            _popularMovie.value = Resource.loading()
            movieRepository.getNowPlaying(
                Locale.getDefault().toLanguageTag()
            ).collect {
                _popularMovie.value = it
            }
        }
    }
}

