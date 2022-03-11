package com.codingtok.hmovies.ui.home

import android.content.res.Resources
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.databinding.LayoutHorizontalDiscoverBinding
import com.codingtok.hmovies.databinding.LayoutHorizontalMovieBinding
import com.codingtok.hmovies.databinding.LayoutSliderBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter


class HomeListAdapter(
    private val resources: Resources
): BaseListAdapter<Issue<*>, ViewDataBinding>(diffCallback) {

    companion object diffCallback : DiffUtil.ItemCallback<Issue<*>>() {
        override fun areItemsTheSame(
            oldItem: Issue<*>,
            newItem: Issue<*>
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Issue<*>,
            newItem: Issue<*>
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return when (viewType) {
            Issue.LayoutType.SLIDER_LAYOUT -> {
                R.layout.layout_slider
            }
            Issue.LayoutType.RECYCLER_DISCOVER_LAYOUT -> {
                R.layout.layout_horizontal_discover
            }
            else -> {
                R.layout.layout_horizontal_movie
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutType
    }

    override fun bindView(binding: ViewDataBinding, item: Issue<*>?, position: Int) {
        if (item != null) {
            when (binding) {
                is LayoutSliderBinding -> {
                    val adapter = BannerAdapter()
                    val data = (item as Issue<Movie.Slim>).mediaTypeItemList.subList(0, 5)
                    binding.apply {
                        bannerNowPlaying.setSliderAdapter(adapter)
                        bannerNowPlaying.startAutoCycle()
                        circleIndicator.createIndicators(0, 0)
                        bannerNowPlaying.setCurrentPageListener {
                            circleIndicator.animatePageSelected(it)
                        }
                        adapter.registerDataSetObserver(circleIndicator.dataSetObserver)
                        circleIndicator.createIndicators(data.size, 0)
                    }
                    adapter.renewItems(data)
                }
                is LayoutHorizontalMovieBinding -> {
                    val adapter = MoviesListAdapter()
                    binding.apply {
                        btnTrending.buttonTitle = item.title.toString()
                        trendingRecyclerview.adapter = adapter
                    }
                    adapter.submitList((item as Issue<Movie.Slim>).mediaTypeItemList)
                }
                is LayoutHorizontalDiscoverBinding -> {
                    val adapter = DiscoverListAdapter(resources)
                    binding.apply {
                        collectionActionButton.buttonTitle = item.title.toString()
                        recyclerview.adapter = adapter
                    }
                    adapter.submitList((item as Issue<Genres>).mediaTypeItemList)
                }
            }
        }
    }

}