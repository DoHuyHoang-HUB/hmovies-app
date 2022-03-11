package com.codingtok.hmovies.ui.home

import android.content.res.Resources
import androidx.recyclerview.widget.DiffUtil
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.databinding.ItemDiscoverBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter

class DiscoverListAdapter(
    private val resources: Resources
): BaseListAdapter<Genres, ItemDiscoverBinding>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Genres>() {
        override fun areItemsTheSame(oldItem: Genres, newItem: Genres): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genres, newItem: Genres): Boolean {
            return oldItem == newItem
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_discover
    }

    override fun bindView(binding: ItemDiscoverBinding, item: Genres, position: Int) {
        binding.apply {
            genreName.text = item.name
            imageDiscover.setImageResource(randomBlurImage(position))
            executePendingBindings()
        }
    }

    private fun randomBlurImage(position: Int): Int {
        val blurImg = resources.obtainTypedArray(R.array.blur_image)
        if (position > 9) {
            return blurImg.getResourceId(position % 10, 0)
        } else {
            return blurImg.getResourceId(position, 0)
        }
    }
}