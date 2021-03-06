package com.codingtok.hmovies.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.App
import com.codingtok.hmovies.R

abstract class BaseFragment<ViewBinding: ViewDataBinding, ViewModel: BaseViewModel>: Fragment() {

    private var _viewBinding: ViewBinding? = null
    protected val viewBinding: ViewBinding get() = _viewBinding!!
    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
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
        observerEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun observerEvent() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                handleLoading(it)
            }
            errorMessage.observe(viewLifecycleOwner) {
                handleErrorMessage(it)
            }
            noInternetConnectionEvent.observe(viewLifecycleOwner) {
                handleErrorMessage(getString(R.string.no_internet))
            }
            connectTimeoutEvent.observe(viewLifecycleOwner) {
                handleErrorMessage(requireContext().resources.getString(R.string.no_internet));
            }
            noDataEvent.observe(viewLifecycleOwner) {
                handleErrorMessage(getString(R.string.no_data))
            }
            serverErrorEvent.observe(viewLifecycleOwner) {
                handleErrorMessage(getString(R.string.error_data))
            }
            unknownErrorEvent.observe(viewLifecycleOwner) {
                handleErrorMessage(getString(R.string.unknown_error))
            }
        }
    }

    protected abstract fun handleLoading(isLoading: Boolean)

    protected abstract fun handleErrorMessage(message: String?)
}