package com.codingtok.hmovies.ui.base.loadmorerefesh

import androidx.databinding.ViewDataBinding
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel

abstract class BaseLoadMoreRefreshFragment<ViewBinding: ViewDataBinding, ViewModel: BaseRefreshViewModel<T>, T: Any>: BaseRefreshFragment<ViewBinding, ViewModel, T>() {
}