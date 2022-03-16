package com.codingtok.hmovies.ui.related

import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.ItemMovieBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.widget.OnItemClickListener

class RelatedMovieListAdapter(
    private val onItemClickListener: OnItemClickListener
): BaseListAdapter<Movie.Slim, ItemMovieBinding>(diffCallback, onItemClickListener) {

    companion object diffCallback: DiffUtil.ItemCallback<Movie.Slim>() {
        override fun areItemsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie.Slim, newItem: Movie.Slim): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun bindView(binding: ItemMovieBinding, item: Movie.Slim, position: Int) {
        binding.root.setOnClickListener { onItemClickListener.onItemClick(item, position) }
    }
}