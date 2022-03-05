package com.codingtok.hmovies.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.databinding.HomeFragmentBinding
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.utils.Resource
import com.codingtok.hmovies.utils.Status
import com.codingtok.hmovies.utils.observe
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.home_fragment

    private lateinit var bannerAdapter: BannerAdapter

    private lateinit var trendingAdapter: MoviesListAdapter

    private lateinit var topRatedAdapter: MoviesListAdapter

    private lateinit var popularAdapter: MoviesListAdapter

    private lateinit var latestAdapter: MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerAdapter = BannerAdapter()
        trendingAdapter = MoviesListAdapter()
        topRatedAdapter = MoviesListAdapter()
        popularAdapter = MoviesListAdapter()
        latestAdapter = MoviesListAdapter()

        viewBinding.apply {
            bannerNowPlaying.setSliderAdapter(bannerAdapter)
            trendingRecyclerview.adapter = trendingAdapter
            topRatedRecyclerview.adapter = topRatedAdapter
            popularRecyclerview.adapter = popularAdapter
            latestRecyclerview.adapter = latestAdapter
            circleIndicator.createIndicators(0, 0)
            bannerNowPlaying.setCurrentPageListener {
                circleIndicator.animatePageSelected(it)
            }
            bannerAdapter.registerDataSetObserver(circleIndicator.dataSetObserver)
        }

        observe(viewModel.nowPlayingMovie, ::handleMoviesNowPlaying)
        observe(viewModel.trendingMovie, ::handleTrending)
        observe(viewModel.topRatedMovie, ::handleTopRated)
        observe(viewModel.popularMovie, ::handlePopularMovie)
        observe(viewModel.latestMovie, ::handleLatestMovie)
    }

    private fun handleMoviesNowPlaying(status: Resource<Page<Movie.Slim>>) {
        when (status.status) {
            Status.LOADING -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }
            Status.SUCCESS -> {
                val data = status.data?.results?.subList(0, 5)
                bannerAdapter.renewItems(data)
                viewBinding.circleIndicator.createIndicators(5, 0)
            }
            Status.ERROR -> {
                var error = ""
                status.error?.let {
                    error = it.message
                }
                Toast.makeText(requireContext(), "${status.message}, $error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleTrending(status: Resource<Page<MediaTypeItem>>) {
        when (status.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {
                trendingAdapter.submitList(status.data?.results)
            }
            Status.ERROR -> {
            }
        }
    }

    private fun handleTopRated(status: Resource<Page<Movie.Slim>>) {
        when (status.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {
                topRatedAdapter.submitList(status.data?.results)
            }
            Status.ERROR -> {
            }
        }
    }

    private fun handlePopularMovie(status: Resource<Page<Movie.Slim>>) {
        when (status.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {
                popularAdapter.submitList(status.data?.results)
            }
            Status.ERROR -> {
            }
        }
    }

    private fun handleLatestMovie(status: Resource<Page<Movie.Slim>>) {
        when (status.status) {
            Status.LOADING -> {

            }
            Status.SUCCESS -> {
                latestAdapter.submitList(status.data?.results)
            }
            Status.ERROR -> {
            }
        }
    }
}