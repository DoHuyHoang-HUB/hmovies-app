package com.codingtok.hmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.databinding.HomeFragmentBinding
import com.codingtok.hmovies.utils.Resource
import com.codingtok.hmovies.utils.Status
import com.codingtok.hmovies.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(homeViewModel.popularMovie, ::handleMoviesDiscover)
    }

    private fun handleMoviesDiscover(status: Resource<Page<Movie.Slim>>) {
        when (status.status) {
            Status.LOADING -> {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }
            Status.SUCCESS -> {
                Toast.makeText(requireContext(), "Movie size: ${status.data?.results?.size}", Toast.LENGTH_SHORT).show()
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