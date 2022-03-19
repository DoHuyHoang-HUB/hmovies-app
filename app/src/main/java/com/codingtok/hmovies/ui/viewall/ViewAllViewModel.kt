package com.codingtok.hmovies.ui.viewall

import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.repository.MovieRepository
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): BaseRefreshViewModel<Movie.Slim>() {
    override fun loadData(page: Int) {
        // nothing
        return
    }

    override fun loadData(page: Int, param: Any) {

    }
}