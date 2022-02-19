package com.codingtok.hmovies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.network.api.Api
import com.haroldadmin.cnradapter.invoke
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    private val _popularMovie = MutableLiveData<List<Movie.Slim>>()
    val popularMovie: LiveData<List<Movie.Slim>> = _popularMovie

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            try {
                _popularMovie.value = Api.movieService.getPopular(languageTag = Locale.getDefault().toLanguageTag()).invoke()!!.results.subList(0, 4)
            } catch (e: Exception) {
                _popularMovie.value = listOf()
            }
        }
    }
}