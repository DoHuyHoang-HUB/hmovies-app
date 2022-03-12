package com.codingtok.hmovies.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingtok.hmovies.R
import com.codingtok.hmovies.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_splash

    override fun initView() {
    }

    override fun initData() {
    }

    override fun lazyLoad() {
    }
}