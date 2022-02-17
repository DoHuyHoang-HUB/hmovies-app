package com.codingtok.hmovies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.network.toBaseException
import com.codingtok.hmovies.utils.SingleMutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {
    val isLoading by lazy { SingleMutableLiveData<Boolean>().apply { value = false } }

    val noInternetConnectionEvent by lazy { SingleMutableLiveData<Unit>() }
    val connectTimeOutEvent by lazy { SingleMutableLiveData<Unit>() }
    val errorMessage by lazy { SingleMutableLiveData<String>() }
    val unknownErrorEvent by lazy { SingleMutableLiveData<Unit>() }
    val connectTimeoutEvent by lazy { SingleMutableLiveData<Unit>() }
    val forceUpdateAppEvent by lazy { SingleMutableLiveData<Unit>() }
    val serverMaintainEvent by lazy { SingleMutableLiveData<Unit>() }

    // exception handler for coroutine
    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { context, throwable ->
            viewModelScope.launch {
                onError(throwable)
            }
        }
    }
    protected val viewModelScopeExceptionHandler by lazy { viewModelScope + exceptionHandler }

    /**
     * handle throwable when load fail
     */
    protected open fun onError(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> {
                noInternetConnectionEvent.call()
            }
            is ConnectException -> {
                noInternetConnectionEvent.call()
            }
            is SocketTimeoutException -> {
                connectTimeOutEvent.call()
            }
            else -> {
                val baseException = throwable.toBaseException()
                when (baseException.httpCode) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        errorMessage.value = baseException.message
                    }
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        errorMessage.value = baseException.message
                    }
                    else -> {
                        unknownErrorEvent.call()
                    }
                }
            }
        }
        hideLoading()
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun showLoading() {
        _isLoading.value = true
    }

    fun hideLoading() {
        _isLoading.value = false
    }
}