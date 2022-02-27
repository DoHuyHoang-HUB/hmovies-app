package com.codingtok.hmovies.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.ItemHomeBannerBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter

class BannerAdapter: BaseListAdapter<Movie, ItemHomeBannerBinding>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem;
        }

    }

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_home_banner;
    }

}