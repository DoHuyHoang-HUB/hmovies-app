package com.codingtok.hmovies.ui.related

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.BUNDLE_MOVIE_DATA
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Genres
import com.codingtok.hmovies.data.model.Movie
import com.codingtok.hmovies.databinding.RelatedMovieFragmentBinding
import com.codingtok.hmovies.ui.activity.MovieDetailActivity
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.baserefresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.widget.OnItemClickListener

class RelatedMovieFragment : BaseRefreshFragment<RelatedMovieFragmentBinding, RelatedMovieViewModel, Movie.Slim>() {

    private var movieId: Int? = null

    override val viewModel: RelatedMovieViewModel by viewModels()
    override val layoutId: Int = R.layout.related_movie_fragment

    override val recyclerView: RecyclerView = viewBinding.recommendationsRecyclerview
    override val listAdapter: BaseListAdapter<Movie.Slim, out ViewDataBinding> by lazy {
        RelatedMovieListAdapter(mOnItemClicK)
    }

    private val mOnItemClicK: OnItemClickListener = object: OnItemClickListener {
        override fun onItemClick(obj: Any?, position: Int) {
        }
    }

    override val mLayoutStatusView: MultipleStatusView = viewBinding.layoutStatusView

    override fun handleLoading(isLoading: Boolean) {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun lazyLoad() {

    }

    override fun handleRefresh(isRefreshing: Boolean) {

    }

    override fun handleEmpty(isEmptyList: Boolean) {

    }

}