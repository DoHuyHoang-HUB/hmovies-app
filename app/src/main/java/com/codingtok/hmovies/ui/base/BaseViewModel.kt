package com.codingtok.hmovies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Error
import com.codingtok.hmovies.data.network.toBaseException
import com.codingtok.hmovies.utils.SingleMutableLiveData
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus


open class BaseViewModel : ViewModel() {
    val isLoading by lazy { SingleMutableLiveData<Boolean>().apply { value = false } }

    val errorMessage by lazy { SingleMutableLiveData<String>() }

    val noInternetConnectionEvent by lazy { SingleMutableLiveData<Unit>() }
    val connectTimeoutEvent by lazy { SingleMutableLiveData<Unit>() }
    val serverErrorEvent by lazy { SingleMutableLiveData<Unit>() }
    val unknownErrorEvent by lazy { SingleMutableLiveData<Unit>() }
    val noDataEvent by lazy { SingleMutableLiveData<Unit>() }

    /**
     * error type
     */
    protected open fun onError(response: NetworkResponse<*, *>) {
        when (response) {
            is NetworkResponse.NetworkError -> {
                errorMessage.value = response.error.message
                connectTimeoutEvent.call()
            }
            is NetworkResponse.ServerError -> {
//                errorMessage.value = response.error.message
                serverErrorEvent.call()
            }
            is NetworkResponse.UnknownError -> {
//                errorMessage.value = response.error.message
                unknownErrorEvent.call()
            }
            else -> {}
        }
        hideLoading()
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}

