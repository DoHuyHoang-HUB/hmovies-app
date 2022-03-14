package com.codingtok.hmovies.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingtok.hmovies.R
import com.codingtok.hmovies.databinding.MovieDetailFragmentBinding
import com.codingtok.hmovies.ui.episode.EpisodeFragment
import com.codingtok.hmovies.ui.related.RelatedMovieFragment
import com.codingtok.hmovies.ui.trailer.TrailerFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = FragmentPagerItems(requireContext())
        pager.add(FragmentPagerItem.of(getString(R.string.related_movie), RelatedMovieFragment::class.java))
        pager.add(FragmentPagerItem.of(getString(R.string.trailer), TrailerFragment::class.java))
        pager.add(FragmentPagerItem.of(getString(R.string.episode_movie), EpisodeFragment::class.java))

        val adapter = FragmentPagerItemAdapter(
            requireActivity().supportFragmentManager, pager
        )

        binding.apply {
            viewPager.adapter = adapter
            tablayout.setupWithViewPager(viewPager)
        }
    }

}