package com.codingtok.hmovies.ui.base.refresh

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.ui.base.BaseListAdapter

abstract class BaseRefreshFragment<ViewBinding: ViewDataBinding, ViewModel: BaseRefreshViewModel<T>, T: Any>: BaseFragment<ViewBinding, ViewModel>() {

    abstract val recyclerView: RecyclerView?
    abstract val listAdapter: BaseListAdapter<T, out ViewDataBinding>?

    protected abstract val mLayoutStatusView: MultipleStatusView?

    open fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRefresh()
        initView()
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open fun setupRefresh() {
        recyclerView?.layoutManager = getLayoutManager()
        recyclerView?.adapter = listAdapter
        recyclerView?.setHasFixedSize(true)
        viewModel.apply {
            itemList.observe(viewLifecycleOwner) { itemList ->
                itemList?.let {
                    listAdapter?.submitList(it)
                }
            }
            firstLoad()
            isRefreshing.observe(viewLifecycleOwner) {
                handleRefresh(it)
            }
            isEmptyList.observe(viewLifecycleOwner) {
                handleEmpty(it)
            }
        }
    }

    protected open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    protected open fun initView() {}

    protected abstract fun lazyLoad()

    protected abstract fun handleRefresh(isRefreshing: Boolean)

    protected abstract fun handleEmpty(isEmptyList: Boolean)
}