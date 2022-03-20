package com.codingtok.hmovies.ui.viewall

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.ViewAllFragmentBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.loadmorerefesh.BaseLoadMoreRefreshFragment
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.widget.OnItemClickListener
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ViewAllFragment : BaseLoadMoreRefreshFragment<ViewAllFragmentBinding, ViewAllViewModel, Movie.Slim>() {

    override val viewModel: ViewAllViewModel by viewModels()
    override val layoutId: Int = R.layout.view_all_fragment

    private val navigationArgs: ViewAllFragmentArgs by navArgs()

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

    override val recyclerView: RecyclerView
        get() = viewBinding.viewAllRecyclerView

    override val refreshLayout: SmartRefreshLayout
        get() = viewBinding.smartRefreshLayout

    override fun getLayoutManager(): RecyclerView.LayoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

    override val listAdapter: BaseListAdapter<Movie.Slim, out ViewDataBinding> by lazy {
        ViewAllListAdapter(onItemClick = mOnItemClick)
    }

    override fun initView() {
        viewModel.setParam(navigationArgs.view)
        viewBinding.headerTitle.text = navigationArgs.headerTitle
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