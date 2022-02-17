package com.codingtok.hmovies.ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewBinding: ViewDataBinding, ViewModel: BaseViewModel>: Fragment() {
    protected lateinit var viewBinding: ViewBinding
    protected abstract val viewMode: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int
}