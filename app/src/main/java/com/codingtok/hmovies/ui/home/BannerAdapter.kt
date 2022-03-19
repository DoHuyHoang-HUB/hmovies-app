package com.codingtok.hmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.ItemHomeBannerBinding
import com.codingtok.hmovies.ui.widget.OnItemClickListener
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class BannerAdapter(
    private val onItemClick: OnItemClickListener
): SliderViewAdapter<BannerAdapter.BannerHolder>() {
    private var sliderItems: List<Movie.Slim> = listOf()

    fun renewItems(sliderItems: List<Movie.Slim>?) {
        this.sliderItems = sliderItems ?: listOf()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return sliderItems.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerHolder {
        return BannerHolder(ItemHomeBannerBinding.inflate(LayoutInflater.from(parent?.context)))
    }

    override fun onBindViewHolder(viewHolder: BannerHolder?, position: Int) {
        val item = sliderItems.get(position)
        viewHolder?.bind(item)
        viewHolder?.itemView?.setOnClickListener { onItemClick.onItemClick(item, position) }
    }

    class BannerHolder(private val binding: ItemHomeBannerBinding): SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(movie: Movie.Slim) {
            binding.apply {
                item = movie
            }
        }
    }
}