package com.codingtok.hmovies.ui.home

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.TVShow
import com.codingtok.hmovies.databinding.ItemMovieBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter

class MoviesListAdapter: BaseListAdapter<MediaTypeItem, ItemMovieBinding>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<MediaTypeItem>() {
        override fun areItemsTheSame(oldItem: MediaTypeItem, newItem: MediaTypeItem): Boolean {
            return when {
                oldItem is Movie && newItem is Movie -> oldItem.id == newItem.id
                oldItem is Movie.Slim && newItem is Movie.Slim -> oldItem.id == newItem.id
                oldItem is TVShow && newItem is TVShow -> oldItem.id == newItem.id
                oldItem is TVShow.Slim && newItem is TVShow.Slim -> oldItem.id == newItem.id
                else -> false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MediaTypeItem, newItem: MediaTypeItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }
}