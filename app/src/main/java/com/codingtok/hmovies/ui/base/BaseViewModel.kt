package com.codingtok.hmovies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtok.hmovies.data.network.toBaseException
import com.codingtok.hmovies.utils.SingleLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BaseViewModel : ViewModel() {
    val isLoading by lazy { SingleLiveData<Boolean>().apply { value = false } }

    val noInternetConnectionEvent by lazy { SingleLiveData<Unit>() }
    val connectTimeOutEvent by lazy { SingleLiveData<Unit>() }
    val errorMessage by lazy { SingleLiveData<String>() }
    val unknownErrorEvent by lazy { SingleLiveData<Unit>() }

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
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}