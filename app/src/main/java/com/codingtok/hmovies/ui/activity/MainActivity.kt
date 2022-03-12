package com.codingtok.hmovies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codingtok.hmovies.R
import com.codingtok.hmovies.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    override fun initData() {
    }

    override fun lazyLoad() {
    }
}