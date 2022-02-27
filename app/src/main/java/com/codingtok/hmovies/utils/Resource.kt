package com.codingtok.hmovies.utils

import com.codingtok.hmovies.data.model.Error

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val error: Error.DefaultError?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String?, error: Error.DefaultError? = null): Resource<T> {
            return Resource(Status.ERROR, null, msg, error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }

    }
}