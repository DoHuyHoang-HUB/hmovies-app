package com.codingtok.hmovies.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.codingtok.hmovies.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerAdapter = BannerAdapter()

        viewBinding.apply {
            bannerNowPlaying.setSliderAdapter(bannerAdapter)
            circleIndicator.createIndicators(0, 0)
            bannerNowPlaying.setCurrentPageListener {
                circleIndicator.animatePageSelected(it)
            }
            bannerAdapter.registerDataSetObserver(circleIndicator.dataSetObserver)
        }

        observe(viewModel.popularMovie, ::handleMoviesDiscover)
    }

    private fun handleMoviesDiscover(status: Resource<Page<Movie.Slim>>) {
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

}