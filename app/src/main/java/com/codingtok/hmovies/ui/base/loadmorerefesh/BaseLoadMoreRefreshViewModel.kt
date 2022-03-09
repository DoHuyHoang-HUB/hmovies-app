package com.codingtok.hmovies.ui.base.loadmorerefesh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingtok.hmovies.DEFAULT_FIRST_PAGE
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshViewModel

abstract class BaseLoadMoreRefreshViewModel<T>() : BaseRefreshViewModel<T>() {

}