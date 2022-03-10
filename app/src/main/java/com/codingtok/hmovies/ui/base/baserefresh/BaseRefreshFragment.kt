package com.codingtok.hmovies.ui.base.baserefresh

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.hmovies.ui.base.BaseFragment
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.BaseViewModel

abstract class BaseRefreshFragment<ViewBinding: ViewDataBinding, ViewModel: BaseRefreshViewModel<T>, T: Any>: BaseFragment<ViewBinding, ViewModel>() {

    abstract val recyclerView: RecyclerView?
    abstract val listAdapter: BaseListAdapter<T, out ViewDataBinding>?

    open fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRefresh()
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
        }
    }
}