package com.codingtok.hmovies.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.ui.home.HomeAdapter

@BindingAdapter("loadUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        this.load(imgUri) {
            placeholder(R.drawable.logo)
        }
    }
}

@BindingAdapter( "listData")
fun ViewPager2.bindViewPager(data: List<Movie.Slim>) {
    val adapter = this.adapter as HomeAdapter
    adapter.submitList(data)
}