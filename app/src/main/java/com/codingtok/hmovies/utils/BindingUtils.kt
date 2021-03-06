package com.codingtok.hmovies.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.codingtok.hmovies.App
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.model.Person
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.ui.home.BannerAdapter
import com.codingtok.hmovies.ui.home.DiscoverListAdapter
import com.codingtok.hmovies.ui.home.MoviesListAdapter
import com.codingtok.hmovies.ui.widget.CollectionActionButton
import com.codingtok.hmovies.ui.widget.OnItemClickListener
import com.scwang.smartrefresh.layout.SmartRefreshLayout
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

@BindingAdapter("listItem", "onItemClick")
fun SliderView.bindListItem(listItem: List<*>, onItemClick: OnItemClickListener) {
    val adapter = BannerAdapter(onItemClick)
    this.setSliderAdapter(adapter)
    adapter.renewItems(listItem as List<Movie.Slim>)
}

@BindingAdapter("listItem", "onItemClick", "onViewAllClick")
fun MultiSnapRecyclerView.bindListItem(
    listItem: Issue<*>,
    onItemClick: OnItemClickListener,
    onViewAllClick: (String, String) -> Unit
) {
    val adapter = MoviesListAdapter(onItemClick, onViewAllClick, listItem.type, listItem.title)
    this.adapter = adapter
    adapter.submitList(listItem.mediaTypeItemList as List<Movie.Slim>)
}

@BindingAdapter("listItem", "onItemClick")
fun MultiSnapRecyclerView.bindListItem(listItem: List<*>, onItemClick: OnItemClickListener) {
    val adapter = DiscoverListAdapter(App.context.resources, onItemClick)
    this.adapter = adapter
    adapter.submitList(listItem as List<Genres>)
}

@BindingAdapter("title")
fun CollectionActionButton.bindTitle(title: String) {
    this.buttonTitle = title
}

@BindingAdapter("appendListText")
fun TextView.bindText(listText: List<Pair<Person.Slim, *>>?) {
    when (listText?.first()?.second) {
        is Person.CastRole -> {
            this.text = App.context.resources.getString(
                R.string.cast_name,
                listText.joinToString(separator = ", ") { it.first.name })
        }
        is Person.CrewJob -> {
            this.text = App.context.resources.getString(
                R.string.director_name,
                listText.joinToString(separator = ", ") { it.first.name })
        }
    }
}

@BindingAdapter("isRefreshing")
fun SmartRefreshLayout.bindRefreshing(refreshing: Boolean?) {

}

@BindingAdapter("onScrollListener")
fun RecyclerView.bindOnScrollListener(listener: RecyclerView.OnScrollListener?) {
    if (listener != null) addOnScrollListener(listener)
}


