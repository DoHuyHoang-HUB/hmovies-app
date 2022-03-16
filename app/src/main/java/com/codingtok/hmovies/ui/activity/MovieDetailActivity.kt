package com.codingtok.hmovies.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codingtok.hmovies.R
import com.codingtok.hmovies.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.codingtok.hmovies.BUNDLE_MOVIE_DATA
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.ui.moviedetail.MovieDetailViewModel

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun layoutId(): Int = R.layout.activity_movie_detail

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_movie_detail) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun initData() {
        val movieId = intent.getSerializableExtra(BUNDLE_MOVIE_DATA) as Int
        Toast.makeText(this, "$movieId", Toast.LENGTH_SHORT).show()
        viewModel.getMovieDetail(movieId);
    }

    override fun lazyLoad() {
    }
}