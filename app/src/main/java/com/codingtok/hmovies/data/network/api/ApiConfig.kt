package com.codingtok.hmovies.data.network.api

import java.util.*

object ApiConfig {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val KEY_API = "fbf0ee04b79e75b57d0a73e59372e9d4"
    val originalImage: (String) -> String = { imgPath ->
        "https://image.tmdb.org/t/p/original/${imgPath}"
    }
    val w500Image: (String) -> String = { imgPath ->
        "https://image.tmdb.org/t/p/w500/${imgPath}"
    }
}