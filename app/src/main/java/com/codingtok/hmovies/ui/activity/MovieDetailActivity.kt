package com.codingtok.hmovies.ui.activity

import androidx.activity.viewModels
import com.codingtok.hmovies.R
import com.codingtok.hmovies.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.codingtok.hmovies.BUNDLE_MOVIE_DATA
import com.codingtok.hmovies.ui.moviedetail.MovieDetailFragment
import com.codingtok.hmovies.ui.moviedetail.MovieDetailViewModel

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_movie_detail

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun initView() {
    }

    override fun initData() {
        val movieId = intent.getSerializableExtra(BUNDLE_MOVIE_DATA) as Int
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, MovieDetailFragment.newInstance(movieId))
            .commit()
    }

    override fun lazyLoad() {
    }
}