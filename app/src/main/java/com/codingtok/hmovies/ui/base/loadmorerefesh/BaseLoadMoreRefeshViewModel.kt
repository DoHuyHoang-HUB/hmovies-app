package com.codingtok.hmovies.ui.base.loadmorerefesh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingtok.hmovies.data.constants.Constants
import com.codingtok.hmovies.ui.base.BaseViewModel

abstract class BaseLoadMoreRefeshViewModel<T>() : BaseViewModel() {

    // refresh flag
    private val _isRefreshing = MutableLiveData<Boolean>().apply { value = false }
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    // load more flag
    private val isLoadMore = MutableLiveData<Boolean>().apply { value = false }


    // current page
    private val currentPage = MutableLiveData<Int>().apply { value = getPreFirstPage() }

    private val isLastPage = MutableLiveData<Boolean>().apply { value = false }

    private val _itemList = MutableLiveData<List<T>>()
    val itemList: LiveData<List<T>> = _itemList


    private val _isEmptyList = MutableLiveData<Boolean>().apply { value = false }
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    // load data
    abstract fun loadData(page: Int)

    /**
     * check first time load data
     */
    private fun isFirst() =
        currentPage.value == getPreFirstPage() && itemList.value?.isEmpty() ?: true

    /**
     * first load
     */
    fun firstLoad() {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage())
        }
    }

    fun doRefresh() {
        if (isLoading.value == true || isRefreshing.value == true) return
        _isRefreshing.value = true
        refreshData()
    }

    /**
     * load first page
     */
    protected fun refreshData() {
        loadData(getFirstPage())
    }

    private fun getPreFirstPage() = getFirstPage() - 1

    /**
     * override if first page is not 1
     */
    open fun getFirstPage() = Constants.DEFAULT_FIRST_PAGE

    fun doLoadMore() {
        if (isLoading.value == true
            || isRefreshing.value == true
            || isLoadMore.value == true
            || isLastPage.value == true
        ) return
        isLoadMore.value = true
        loadMore()
    }

    protected fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: getFirstPage())
    }

    fun onLoadSuccess(page: Int, item: List<T>) {
        // load success then update current page
        currentPage.value = page
        // case load first then clear data from listItem
        if (currentPage.value == getFirstPage()) _itemList.value = listOf()

        // add new data listitem
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        _isRefreshing.value = false
        isLoadMore.value = false

        // check empty list
        checkEmptyList()
    }

    /**
     * check list is empty
     */
    private fun checkEmptyList() {
        _isEmptyList.value = itemList.value?.isEmpty() ?: true
    }
}