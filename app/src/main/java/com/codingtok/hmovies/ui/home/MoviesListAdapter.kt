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

class MoviesListAdapter: BaseListAdapter<Movie.Slim, ItemMovieBinding>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Movie.Slim>() {
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
        binding.apply {
            item.poster?.get(Image.Quality.POSTER_W_780)?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                imageMovie.load(imgUri) {
                    placeholder(R.drawable.logo)
                }
            }
            nameMovie.text = item.title
        }
    }
}