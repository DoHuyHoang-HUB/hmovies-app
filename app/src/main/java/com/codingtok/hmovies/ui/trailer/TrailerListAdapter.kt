package com.codingtok.hmovies.ui.trailer

import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Video
import com.codingtok.hmovies.databinding.ItemTrailerVideoBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.widget.OnItemClickListener

class TrailerListAdapter(
    onItemClick: OnItemClickListener
): BaseListAdapter<Video, ItemTrailerVideoBinding>(diffCallback, onItemClick) {

    companion object diffCallback: DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_trailer_video
}