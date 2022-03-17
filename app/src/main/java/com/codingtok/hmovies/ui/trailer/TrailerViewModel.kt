package com.codingtok.hmovies.ui.trailer

import com.codingtok.hmovies.data.model.Video
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshViewModel

class TrailerViewModel: BaseRefreshViewModel<Video>() {
    override fun loadData(page: Int) {
        // nothing
        return
    }

    override fun loadData(page: Int, param: Any) {

    }
}