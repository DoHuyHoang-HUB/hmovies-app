package com.codingtok.hmovies.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.codingtok.common.MultipleStatusView
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
class HomeFragment : BaseRefreshFragment<HomeFragmentBinding, HomeViewModel, Issue<*>>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.home_fragment

    override val recyclerView: RecyclerView?
        get() = viewBinding.homeRecyclerView

    override val listAdapter: BaseListAdapter<Issue<*>, out ViewDataBinding> by lazy {
        HomeListAdapter(requireContext().resources)
    }

    override val mLayoutStatusView: MultipleStatusView?
        get() = viewBinding.layoutStatusView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            mLayoutStatusView?.showLoading()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun lazyLoad() {

    }

    override fun handleRefresh(isRefreshing: Boolean) {

    }

    override fun handleEmpty(isEmptyList: Boolean) {

    }
}