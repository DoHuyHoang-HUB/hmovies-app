package com.codingtok.hmovies.ui.related

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel
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

    override fun loadData(page: Int) {
        // nothing
        return
    }

    override fun loadData(page: Int, param: Any) {
        viewModelScope.launch {
            movieRepository.getRecommendations(
                movieId = param as Int,
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