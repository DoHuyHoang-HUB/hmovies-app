package com.codingtok.hmovies.ui.base.refresh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingtok.hmovies.DEFAULT_FIRST_PAGE
import com.codingtok.hmovies.ui.base.BaseViewModel

abstract class BaseRefreshViewModel<T>: BaseViewModel() {
    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    fun doRefresh() {
        if (isLoading.value == true || isRefreshing.value == true) return
        isRefreshing.value = true
        refreshData()
    }

    private val _itemList = MutableLiveData<List<T>>()
    val itemList: LiveData<List<T>> get() = _itemList

    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    /**
     * check first time load data
     */
    protected open fun isFirst() = itemList.value?.isEmpty() ?: true;

    /**
     * first load
     */
    fun firstLoad() {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage())
        }
    }

    /**
     * first load with param
     * @param param
     */
    fun firstLoad(param: Any) {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage(), param)
        }
    }

    /**
     * load first page
     */
    protected fun refreshData() {
        loadData(getFirstPage())
    }

    /**
     * override if first page is not 1
     */
    open fun getFirstPage() = DEFAULT_FIRST_PAGE

    /**
     * load data
     * @param page
     */
    abstract fun loadData(page: Int)

    /**
     * load data
     * @param page
     * @param param
     */
    abstract fun loadData(page: Int, param: Any)


    /**
     * handle load success
     */
    open fun onLoadSuccess(items: List<T>?) {
        _itemList.value = items ?: listOf()

        hideLoading()
        isRefreshing.value = false;

        checkEmptyList()
    }

    /**
     * Check empty list
     */
    private fun checkEmptyList() {
        isEmptyList.value = _itemList.value?.isEmpty() ?: true
    }

    /**
     * remove all item
     */
     fun removeAllItem() {
        _itemList.value = listOf()
        checkEmptyList()
    }
}