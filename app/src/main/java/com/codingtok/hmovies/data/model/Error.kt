package com.codingtok.hmovies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

sealed class Error: Serializable {
    companion object {
        fun isAnyError(clazz: Class<*>): Boolean {
            return clazz == Error::class.java
                    || clazz == DefaultError::class.java
                    || clazz == PostError::class.java
        }
    }

    @JsonClass(generateAdapter = true)
    data class PostError internal constructor(
        val errors: List<String>
    ) : Error(), Serializable

    @JsonClass(generateAdapter = true)
    data class DefaultError internal constructor(
        @Json(name = "status_code")
        val code: Int,
        @Json(name = "status_message")
        val message: String
    ) : Error(), Serializable
}