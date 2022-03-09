package com.codingtok.hmovies.ui.base.loadmorerefesh

import androidx.databinding.ViewDataBinding
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshViewModel

abstract class BaseLoadMoreRefreshFragment<ViewBinding: ViewDataBinding, ViewModel: BaseRefreshViewModel<T>, T: Any>: BaseRefreshFragment<ViewBinding, ViewModel, T>() {
}