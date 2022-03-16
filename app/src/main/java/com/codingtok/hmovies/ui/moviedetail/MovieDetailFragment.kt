package com.codingtok.hmovies.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.BUNDLE_MOVIE_DATA
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.MovieDetailFragmentBinding
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.ui.episode.EpisodeFragment
import com.codingtok.hmovies.ui.related.RelatedMovieFragment
import com.codingtok.hmovies.ui.trailer.TrailerFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding, MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by activityViewModels()
    override val layoutId: Int = R.layout.movie_detail_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = FragmentPagerItems(requireContext())
        pager.add(FragmentPagerItem.of(getString(R.string.related_movie), RelatedMovieFragment::class.java))
        pager.add(FragmentPagerItem.of(getString(R.string.trailer), RelatedMovieFragment::class.java))
        pager.add(FragmentPagerItem.of(getString(R.string.episode_movie), RelatedMovieFragment::class.java))

        val adapter = FragmentPagerItemAdapter(
            requireActivity().supportFragmentManager, pager
        )

        viewBinding.apply {
            viewPager.adapter = adapter
            tablayout.setupWithViewPager(viewPager)
        }

    }



    override fun handleLoading(isLoading: Boolean) {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}