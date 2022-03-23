package com.codingtok.hmovies.ui.home

import android.content.Intent
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.BUNDLE_MOVIE_DATA
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.data.model.bean.Issue
import com.codingtok.hmovies.databinding.HomeFragmentBinding
import com.codingtok.hmovies.ui.activity.MovieDetailActivity
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.widget.OnItemClickListener
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseRefreshFragment<HomeFragmentBinding, HomeViewModel, Issue<*>>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.home_fragment

    override val recyclerView: RecyclerView
        get() = viewBinding.homeRecyclerView

    override val refreshLayout: SmartRefreshLayout
        get() = viewBinding.smartRefreshLayout

    override val listAdapter: BaseListAdapter<Issue<*>, out ViewDataBinding> by lazy {
        HomeListAdapter(requireContext().resources, mOnItemClicK) { type, title ->
            val action = HomeFragmentDirections.actionNavHomeToViewAllFragment(type, title);
            findNavController().navigate(action)
        }
    }

    private val mOnItemClicK: OnItemClickListener = object: OnItemClickListener {
        override fun onItemClick(obj: Any?, position: Int) {
            when (obj) {
                is Movie.Slim -> {
                    val action = HomeFragmentDirections.actionNavHomeToMovieDetailFragment(obj.id)
                    findNavController().navigate(action)
                }
                is Genres -> {

                }
            }
        }
    }

    override val mLayoutStatusView: MultipleStatusView
        get() = viewBinding.layoutStatusView

    override fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            mLayoutStatusView.showLoading()
        } else {
            mLayoutStatusView.showContent()
        }
    }


    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun lazyLoad() {

    }

    override fun handleRefresh(isRefreshing: Boolean) {
        if (!isRefreshing) refreshLayout.finishRefresh()
    }

    override fun handleEmpty(isEmptyList: Boolean) {

    }


}