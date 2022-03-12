package com.codingtok.hmovies.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.codingtok.hmovies.App
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.ui.home.BannerAdapter
import com.codingtok.hmovies.ui.home.DiscoverListAdapter
import com.codingtok.hmovies.ui.home.MoviesListAdapter
import com.codingtok.hmovies.ui.widget.CollectionActionButton
import com.smarteist.autoimageslider.SliderView
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView

@BindingAdapter("loadUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        this.load(imgUri) {
            placeholder(R.drawable.logo)
        }
    }
}

@BindingAdapter("listItem")
fun SliderView.bindListItem(listItem: List<*>) {
    val adapter = BannerAdapter()
    this.setSliderAdapter(adapter)
    adapter.renewItems(listItem as List<Movie.Slim>)
}

@BindingAdapter("listItem")
fun MultiSnapRecyclerView.bindListItem(listItem: List<*>) {
    when (listItem.first()) {
        is Movie.Slim -> {
            val adapter = MoviesListAdapter()
            this.adapter = adapter
            adapter.submitList(listItem as List<Movie.Slim>)
        }
        is Genres -> {
            val adapter = DiscoverListAdapter(App.context.resources)
            this.adapter = adapter
            adapter.submitList(listItem as List<Genres>)
        }
    }
}

@BindingAdapter("title")
fun CollectionActionButton.bindTitle(title: String) {
    this.buttonTitle = title
}

