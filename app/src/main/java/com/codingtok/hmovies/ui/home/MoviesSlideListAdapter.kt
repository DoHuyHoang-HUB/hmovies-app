package com.codingtok.hmovies.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.SlideItemContainerBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter

class MoviesSlideListAdapter: BaseListAdapter<Movie.Slim, SlideItemContainerBinding>(callBack) {

    companion object callBack: DiffUtil.ItemCallback<Movie.Slim>() {
        override fun areItemsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem == newItem
        }

    }
    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.slide_item_container
    }

}