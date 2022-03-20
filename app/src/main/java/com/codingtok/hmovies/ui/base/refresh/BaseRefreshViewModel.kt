package com.codingtok.hmovies.ui.base.refresh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingtok.hmovies.DEFAULT_FIRST_PAGE
import com.codingtok.hmovies.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse

abstract class BaseRefreshViewModel<T> : BaseViewModel() {
    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    fun doRefresh() {
        if (isLoading.value == true || isRefreshing.value == true) return
        isRefreshing.value = true
        refreshData()
    }

    protected val _itemList = MutableLiveData<MutableList<T>>()
    val itemList: LiveData<MutableList<T>> get() = _itemList

    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    private val _param = MutableLiveData<Any>().apply { value = null }
    val param: LiveData<Any> = _param

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
            loadData(getFirstPage(), _param.value)

        }
    }

    /**
     * load with param
     */
    fun setParam(param: Any?) {
        _param.value = param
    }


    /**
     * load first page
     */
    protected fun refreshData() {
        loadData(getFirstPage(), _param.value)
    }

    /**
     * override if first page is not 1
     */
    open fun getFirstPage() = DEFAULT_FIRST_PAGE

    /**
     * load data
     * @param page
     * @param param
     */
    abstract fun loadData(page: Int, param: Any?)


    /**
     * handle load success
     */
    open fun onLoadSuccess(page: Int, items: MutableList<T>?) {
        _itemList.value = items ?: mutableListOf()

        hideLoading()
        isRefreshing.value = false;

        checkEmptyList()
    }

    override fun onError(response: NetworkResponse<*, *>) {
        super.onError(response)

        isRefreshing.value = false

        checkEmptyList()
    }

    /**
     * Check empty list
     */
    protected fun checkEmptyList() {
        isEmptyList.value = _itemList.value?.isEmpty() ?: true
    }

    /**
     * remove all item
     */
    fun removeAllItem() {
        _itemList.value = mutableListOf()
        checkEmptyList()
    }
}