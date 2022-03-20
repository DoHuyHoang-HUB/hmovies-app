package com.codingtok.hmovies.data.model.bean

import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page

data class Issue<T: Any>(
    val mediaTypeItemList: List<T>, val title: String?, val layoutType: Int, val type: String? = null
) {
    object LayoutType {
        const val SLIDER_LAYOUT = 1
        const val RECYCLER_LAYOUT = 2
        const val RECYCLER_DISCOVER_LAYOUT = 3
    }
}

