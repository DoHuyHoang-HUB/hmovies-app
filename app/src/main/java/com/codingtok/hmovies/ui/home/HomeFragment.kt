package com.codingtok.hmovies.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.Page
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.databinding.HomeFragmentBinding
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshFragment
import com.codingtok.hmovies.utils.observe
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.takusemba.multisnaprecyclerview.MultiSnapHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseRefreshFragment<HomeFragmentBinding, HomeViewModel, Issue<Movie.Slim>>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.home_fragment

    override val recyclerView: RecyclerView?
        get() = null
//    override val listAdapter: BaseListAdapter<Issue<Movie.Slim>, out ViewDataBinding>
//        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun handleLoading(isLoading: Boolean) {

    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun lazyLoad() {

    }
}