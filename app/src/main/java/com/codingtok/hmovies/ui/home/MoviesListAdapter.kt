package com.codingtok.hmovies.ui.home

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.widget.OnItemClickListener

class MoviesListAdapter(
    private val onItemClick: OnItemClickListener
): BaseListAdapter<Movie.Slim, ViewDataBinding>(DiffCallback, onItemClick) {
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
            ItemType.IS_ITEM -> R.layout.item_movie_title
            else -> R.layout.item_view_all
        }
    }

    override fun bindView(binding: ViewDataBinding, item: Movie.Slim, position: Int) {
        binding.root.setOnClickListener { onItemClick.onItemClick(item, position) }
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