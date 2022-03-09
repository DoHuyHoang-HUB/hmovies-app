package com.codingtok.hmovies.data.model.bean

import com.codingtok.hmovies.data.model.MediaTypeItem
import com.codingtok.hmovies.data.model.Page

data class Issue<T: MediaTypeItem>(
    val mediaTypeItemList: Page<T>, val title: String?, val layoutTypeL: LayoutType
) {
    enum class LayoutType {
        SLIDER_LAYOUT,
        RECYCLER_LAYOUT
    }
}

