package com.codingtok.hmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.enums.Trending
import com.codingtok.hmovies.data.network.api.Api
import com.haroldadmin.cnradapter.invoke
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> = _page

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val trending = Api.trendingService.get(Trending.Type.MOVIE, Trending.TimeWindow.DAY).invoke()!!
                _page.value = trending.totalResults
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}