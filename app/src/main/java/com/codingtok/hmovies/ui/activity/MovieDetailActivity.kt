package com.codingtok.hmovies.ui.activity

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

    private lateinit var movie: Movie.Slim

    override fun layoutId(): Int = R.layout.activity_movie_detail

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_movie_detail) as NavHostFragment

        navController = navHostFragment.navController

        navController.navigate(R.id.movieDetailFragment)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun initData() {
        movie = intent.getSerializableExtra(BUNDLE_MOVIE_DATA) as Movie.Slim
    }

    override fun lazyLoad() {
    }
}