package com.codingtok.hmovies.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Person
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

    private val _cast = MutableLiveData<List<Pair<Person.Slim, Person.CrewJob>>>()
    val cast: LiveData<List<Pair<Person.Slim, Person.CrewJob>>> get() = _cast

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            showLoading()
            movieRepository.getDetail(
                movieId = movieId,
                languageTag = Locale.getDefault().toLanguageTag()
            ).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        _movie.postValue(response.body)
                    }
                    else -> { onError(response) }
                }
            }
            movieRepository.getCreditsCast(
                movieId = movieId,
                languageTag = Locale.getDefault().toLanguageTag()
            ).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        _cast.value = response.body
                    }
                    else -> { onError(response) }
                }
            }
            hideLoading()
        }
    }
}