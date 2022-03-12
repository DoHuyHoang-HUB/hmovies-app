package com.codingtok.hmovies.ui.home

import android.annotation.SuppressLint
import androidx.core.net.toUri
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Image
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.TVShow
import com.codingtok.hmovies.databinding.ItemMovieBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.utils.bindImage

class MoviesListAdapter: BaseListAdapter<Movie.Slim, ViewDataBinding>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Movie.Slim>() {
        override fun areItemsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem == newItem
        }
    }

    object ItemType {
        const val IS_ITEM = 1;
        const val NOT_ITEM = 2;
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            null -> ItemType.NOT_ITEM
            else -> ItemType.IS_ITEM
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return when (viewType) {
            ItemType.IS_ITEM -> R.layout.item_movie
            else -> R.layout.item_view_all
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItem(position: Int): Movie.Slim? {
        if (position == itemCount - 1) {
            return null;
        }
        return super.getItem(position)
    }
}