package com.codingtok.hmovies.ui.trailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.codingtok.common.MultipleStatusView
import com.codingtok.hmovies.R
import com.codingtok.hmovies.data.model.Video
import com.codingtok.hmovies.databinding.TrailerFragmentBinding
import com.codingtok.hmovies.ui.base.BaseListAdapter
import com.codingtok.hmovies.ui.base.refresh.BaseRefreshFragment
import com.codingtok.hmovies.ui.widget.OnItemClickListener

// the fragment initialization parameters, e.g. ARG_MOVIE_ID
private const val ARG_MOVIE_ID = "movieId"

/**
 * A simple [Fragment] subclass.
 * Use the [TrailerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrailerFragment : BaseRefreshFragment<TrailerFragmentBinding, TrailerViewModel, Video>() {

    private var movieId: Int? = null

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param movieId Parameter 1.
         * @return A new instance of fragment TrailerFragment.
         */
        @JvmStatic
        fun newInstance(movieId: Int) =
            TrailerFragment().apply {
                arguments = Bundle().apply {
                    getInt(ARG_MOVIE_ID, movieId)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }
    }

    override val viewModel: TrailerViewModel by viewModels()
    override val layoutId: Int = R.layout.trailer_fragment

    override fun handleLoading(isLoading: Boolean) {

    }

    override fun handleErrorMessage(message: String?) {

    }

    override val recyclerView: RecyclerView get() =
        viewBinding.trailerRecyclerview
    override val listAdapter: BaseListAdapter<Video, out ViewDataBinding> by lazy {
        TrailerListAdapter(mOnItemClick)
    }

    private val mOnItemClick: OnItemClickListener = object: OnItemClickListener {
        override fun onItemClick(obj: Any?, position: Int) {
        }
    }

    override val mLayoutStatusView: MultipleStatusView get() =
        viewBinding.layoutStatusView

    override fun lazyLoad() {

    }

    override fun handleRefresh(isRefreshing: Boolean) {

    }

    override fun handleEmpty(isEmptyList: Boolean) {

    }
}