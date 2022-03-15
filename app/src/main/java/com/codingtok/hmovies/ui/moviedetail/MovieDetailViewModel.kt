package com.codingtok.hmovies.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject
constructor(
    private val movieRepository: MovieRepository
): BaseViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            showLoading()
            movieRepository.getDetail(
                movieId = movieId,
                languageTag = Locale.ROOT.toLanguageTag()
            ).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        _movie.postValue(response.body)
                    }
                    else -> { onError(response) }
                }
            }
            hideLoading()
        }
    }
}