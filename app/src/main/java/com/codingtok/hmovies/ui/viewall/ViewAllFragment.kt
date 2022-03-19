package com.codingtok.hmovies.ui.viewall

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.ViewAllFragmentBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.widget.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ViewAllFragment : BaseRefreshFragment<ViewAllFragmentBinding, ViewAllViewModel, Movie.Slim>() {

    override val viewModel: ViewAllViewModel by viewModels()
    override val layoutId: Int = R.layout.view_all_fragment

    private val navigationArgs: ViewAllFragmentArgs by navArgs()

    override fun handleLoading(isLoading: Boolean) {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override val recyclerView: RecyclerView
        get() = viewBinding.viewAllRecyclerView
    override val listAdapter: BaseListAdapter<Movie.Slim, out ViewDataBinding> by lazy {
        ViewAllListAdapter(onItemClick = mOnItemClick)
    }

    override fun setupRefresh() {
        super.setupRefresh()
        val viewType = navigationArgs.view
        viewModel.firstLoad(viewType)
    }

    private val mOnItemClick: OnItemClickListener = object: OnItemClickListener {
        override fun onItemClick(obj: Any?, position: Int) {
            Toast.makeText(requireContext(), "${position}", Toast.LENGTH_SHORT).show()
        }
    }

    override val mLayoutStatusView: MultipleStatusView
        get() = viewBinding.layoutStatusView

    override fun lazyLoad() {

    }

    override fun handleRefresh(isRefreshing: Boolean) {

    }

    override fun handleEmpty(isEmptyList: Boolean) {

    }

}