package com.codingtok.hmovies.data.model.bean

import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page

data class Issue<T: MediaTypeItem>(
    val mediaTypeItemList: Page<T>, val title: String?, val layoutType: Int
) {
    object LayoutType {
        const val SLIDER_LAYOUT = 1
        const val RECYCLER_LAYOUT = 2
    }
}

