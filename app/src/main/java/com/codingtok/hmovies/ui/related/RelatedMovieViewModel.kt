package com.codingtok.hmovies.ui.related

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RelatedMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): BaseRefreshViewModel<Movie.Slim>() {

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> get() = _movieId

    public fun setMovieId(movieId: Int) {
        this._movieId.value = movieId
    }

    override fun loadData(page: Int) {
        viewModelScope.launch {
            movieRepository.getRecommendations(
                movieId = movieId.value!!,
                languageTag = Locale.getDefault().toLanguageTag(),
                page = page
            ).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> onLoadSuccess(response.body.results)
                    else -> onError(response)
                }
            }
        }
    }
}