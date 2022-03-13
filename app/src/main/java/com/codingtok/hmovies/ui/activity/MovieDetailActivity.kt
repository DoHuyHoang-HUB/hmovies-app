package com.codingtok.hmovies.ui.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codingtok.hmovies.R
import com.codingtok.hmovies.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun layoutId(): Int = R.layout.activity_movie_detail

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_movie_detail) as NavHostFragment

        navController = navHostFragment.navController

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun initData() {
    }

    override fun lazyLoad() {
    }
}