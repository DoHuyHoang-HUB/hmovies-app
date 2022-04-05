package com.codingtok.hmovies.ui.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.codingtok.hmovies.R
import com.codingtok.hmovies.databinding.MovieDetailFragmentBinding
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.ui.base.BaseFragmentAdapter
import com.codingtok.hmovies.ui.episode.EpisodeFragment
import com.codingtok.hmovies.ui.related.RelatedMovieFragment
import com.codingtok.hmovies.ui.trailer.TrailerFragment
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding, MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModels()
    override val layoutId: Int = R.layout.movie_detail_fragment

    private val navigationArgs: MovieDetailFragmentArgs by navArgs()

    private val tabList = arrayListOf<String>()
    private val fragments = arrayListOf<Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = navigationArgs.movieId

        viewModel.getMovieDetail(movieId)

        tabList.add(getString(R.string.related_movie))
        tabList.add(getString(R.string.trailer))
        tabList.add(getString(R.string.episode_movie))


        fragments.add(RelatedMovieFragment.newInstance(movieId))

        fragments.add(TrailerFragment.newInstance(movieId))
        fragments.add(EpisodeFragment.newInstance())

        viewBinding.apply {
            viewPager.adapter = BaseFragmentAdapter(
                childFragmentManager,
                fragments,
                tabList
            )
            tablayout.setupWithViewPager(viewPager)
            btnBack.setOnClickListener { onBack() }
            btnClose.setOnClickListener { onClose() }
        }

        var isShow = false
        var scrollRange = -1

        viewBinding.apply {
            appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1){
                    scrollRange = appBarLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset < 0){
                    textTitle.visibility = View.VISIBLE
                    isShow = true
                } else if (isShow){
                    textTitle.visibility = View.GONE //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            })
        }
    }

    private fun onBack() {
        findNavController().navigateUp()
    }

    private fun onClose() {

        findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToNavHome())
    }

    override fun handleLoading(isLoading: Boolean) {

    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}