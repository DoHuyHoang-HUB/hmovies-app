package com.codingtok.hmovies.ui.home

import androidx.lifecycle.*
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.data.repository.DiscoverRepository
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.data.repository.TrendingRepository
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshViewModel
import com.codingtok.hmovies.utils.SingleMutableLiveData
import com.haroldadmin.cnradapter.NetworkResponse
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
) :BaseRefreshViewModel<Issue<Movie.Slim>>() {

    override fun loadData(page: Int) {
        viewModelScope.launch {
            val itemList = arrayListOf<Issue<Movie.Slim>>()
            movieRepository.getNowPlaying(Locale.getDefault().toLanguageTag()).collect {
                when (val response = it) {
                    is NetworkResponse.Success -> {
                        itemList.add(Issue(response.body, null, Issue.LayoutType.SLIDER_LAYOUT))
                    }
                    else -> onError(response)
                }
            }
        }
    }

}

