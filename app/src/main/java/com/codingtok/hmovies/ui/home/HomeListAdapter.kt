package com.codingtok.hmovies.ui.home

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.ui.base.BaseListAdapter


class HomeListAdapter: BaseListAdapter<Issue<Movie.Slim>, ViewDataBinding>(diffCallback) {

    companion object diffCallback : DiffUtil.ItemCallback<Issue<Movie.Slim>>() {
        override fun areItemsTheSame(
            oldItem: Issue<Movie.Slim>,
            newItem: Issue<Movie.Slim>
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Issue<Movie.Slim>,
            newItem: Issue<Movie.Slim>
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return when (viewType) {
            Issue.LayoutType.SLIDER_LAYOUT -> {
                R.layout.layout_slider
            }
            else -> {
                R.layout.layout_horizontal_movie
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutType
    }

}