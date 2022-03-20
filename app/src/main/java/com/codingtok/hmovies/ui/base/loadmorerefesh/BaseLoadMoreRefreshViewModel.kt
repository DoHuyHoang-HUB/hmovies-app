package com.codingtok.hmovies.ui.base.loadmorerefesh

import androidx.lifecycle.MutableLiveData
import com.codingtok.hmovies.DEFAULT_ITEM_PER_PAGE
import com.codingtok.hmovies.DEFAULT_NUM_VISIBLE_THRESHOLD
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel
import com.codingtok.hmovies.ui.widget.EndlessRecyclerOnScrollListener
import com.haroldadmin.cnradapter.NetworkResponse

abstract class BaseLoadMoreRefreshViewModel<T>() : BaseRefreshViewModel<T>() {

    // load more flag
    private val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    // current page
    private val currentPage = MutableLiveData<Int>().apply { value = getPreFirstPage() }

    // last page flag
    private val isLastPage = MutableLiveData<Boolean>().apply { value = false }

    // scroll listener for recycler view
    val onScrollListener = object: EndlessRecyclerOnScrollListener(getLoadMoreThreshold()) {
        override fun onLoadMore() {
            doLoadMore()
        }
    }

    private fun getPreFirstPage() = getFirstPage() - 1


    /**
     * override if need change number visible threshold
     */
    protected open fun getLoadMoreThreshold() = DEFAULT_NUM_VISIBLE_THRESHOLD

    /**
     * override if need change number item per page
     */
    protected open fun getNumberItemPerPage() = DEFAULT_ITEM_PER_PAGE

    fun doLoadMore() {
        if (isLoading.value == true
            || isRefreshing.value == true
            || isLoadMore.value == true
            || isLastPage.value == true
        ) return
        isLoadMore.value = true
        loadMore()
    }

    /**
     * load next page
     */
    protected fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: getFirstPage(), param.value)
    }

    /**
     * load success
     */
    override fun onLoadSuccess(page: Int, items: MutableList<T>?) {
        // load success then update current page
        currentPage.value = page
        // case load first page then clear data from listItem
        if (currentPage.value == getFirstPage()) itemList.value?.clear()
        // case refresh then reset load more
        if (isRefreshing.value == true) resetLoadMore()

        // add new data to listItem
        val newList = itemList.value ?: ArrayList()
        newList.addAll(items ?: mutableListOf())
        _itemList.value = newList

        // check last page
        isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()

        // reset load
        hideLoading()
        isRefreshing.value = false
        isLoadMore.value = false

        // check empty list
        checkEmptyList()
    }

    /**
     * reset load more
     */
    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
        isLastPage.value = false
    }

    override fun onError(response: NetworkResponse<*, *>) {
        super.onError(response)

        onScrollListener.isLoading = false

        isLoadMore.value = false

        checkEmptyList()
    }
}