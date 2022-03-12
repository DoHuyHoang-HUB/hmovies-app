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
import com.codingtok.hmovies.ui.widget.OnItemClickListener


class HomeListAdapter(
    private val resources: Resources,
    onItemClick: OnItemClickListener
) : BaseListAdapter<Issue<*>, ViewDataBinding>(diffCallback, onItemClick) {

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

    override fun bindView(binding: ViewDataBinding, item: Issue<*>, position: Int) {
        when (binding) {
            is LayoutSliderBinding -> {
                binding.apply {
                    bannerNowPlaying.startAutoCycle()
                    circleIndicator.createIndicators(5, 0)
                    bannerNowPlaying.setCurrentPageListener {
                        circleIndicator.animatePageSelected(it)
                    }
                }
            }
        }
    }

}