package com.codingtok.hmovies.ui.search

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import com.codingtok.hmovies.R
import com.codingtok.hmovies.databinding.SearchFragmentBinding
import com.codingtok.hmovies.ui.activity.SearchActivity
import com.codingtok.hmovies.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutId: Int = R.layout.search_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.layoutSearch.setOnClickListener { openSearchActivity() }
    }

    private fun openSearchActivity() {
        val options = activity?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it, viewBinding.layoutSearch, viewBinding.layoutSearch.transitionName) }
        startActivity(Intent(activity, SearchActivity::class.java), options?.toBundle())
    }

    override fun handleLoading(isLoading: Boolean) {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun handleErrorMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}