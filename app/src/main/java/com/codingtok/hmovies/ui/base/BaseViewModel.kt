package com.codingtok.hmovies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.model.Error
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
    val serverMaintainEvent by lazy { SingleMutableLiveData<Unit>() }
    val unknownErrorEvent by lazy { SingleMutableLiveData<Unit>() }

    protected open fun <T : Any> onError(response: NetworkResponse<T, Error.DefaultError>) {

    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}

