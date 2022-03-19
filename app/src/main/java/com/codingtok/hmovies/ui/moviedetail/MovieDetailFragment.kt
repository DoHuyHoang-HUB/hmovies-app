package com.codingtok.hmovies.ui.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
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

private const val ARG_MOVIE_ID = "movieId"

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding, MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModels()
    override val layoutId: Int = R.layout.movie_detail_fragment

    private val tabList = arrayListOf<String>()
    private val fragments = arrayListOf<Fragment>()

    private var movieId: Int? = null

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_ID, movieId)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDetail(movieId!!)

        tabList.add(getString(R.string.related_movie))
        tabList.add(getString(R.string.trailer))
        tabList.add(getString(R.string.episode_movie))


        fragments.add(RelatedMovieFragment.newInstance(movieId!!))

        fragments.add(TrailerFragment.newInstance(movieId!!))
        fragments.add(EpisodeFragment.newInstance())

        viewBinding.apply {
            viewPager.adapter = BaseFragmentAdapter(
                childFragmentManager,
                fragments,
                tabList
            )
            tablayout.setupWithViewPager(viewPager)
        }

        var isShow = false
        var scrollRange = -1

        viewBinding.apply {
            appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1){
                    scrollRange = appBarLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset < 20){
                    collapsingToolBarLayout.isTitleEnabled = true
                    isShow = true
                } else if (isShow){
                    collapsingToolBarLayout.isTitleEnabled = false //careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            })
        }
    }

    override fun handleLoading(isLoading: Boolean) {

    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}