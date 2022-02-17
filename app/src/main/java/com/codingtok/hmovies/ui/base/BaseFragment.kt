package com.codingtok.hmovies.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.codingtok.hmovies.BR
import com.codingtok.hmovies.R

abstract class BaseFragment<ViewBinding: ViewDataBinding, ViewModel: BaseViewModel>: Fragment() {
    protected lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
            root.isClickable = true
            executePendingBindings()
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerEvents()
    }

    private fun observerEvents() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, {
            })
            errorMessage.observe(viewLifecycleOwner, {
            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, {
            })
            connectTimeoutEvent.observe(viewLifecycleOwner, {
            })
            forceUpdateAppEvent.observe(viewLifecycleOwner, {
            })
            serverMaintainEvent.observe(viewLifecycleOwner, {
            })
            unknownErrorEvent.observe(viewLifecycleOwner, {
            })
        }
    }
}