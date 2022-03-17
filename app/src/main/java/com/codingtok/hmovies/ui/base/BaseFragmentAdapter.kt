package com.codingtok.hmovies.ui.base

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter


class BaseFragmentAdapter : FragmentStatePagerAdapter {

    private var fragments: List<Fragment>? = ArrayList()
    private var titles: List<String>? = null

    constructor(fm: FragmentManager, behavior: Int, fragments: List<Fragment>): super(fm, behavior) {
        this.fragments = fragments
    }

    constructor(fm: FragmentManager, behavior: Int, fragments: List<Fragment>, titles: List<String>): super(fm, behavior) {
        this.titles = titles
        setFragments(fm, fragments, titles)
    }

    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, titles: List<String>) {
        this.titles = titles
        if (this.fragments != null) {
            val ft = fm.beginTransaction()
            fragments.forEach {
                ft.remove(it)
            }
            ft.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragments = fragments
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return fragments!!.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments!!.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (null != titles) titles!!.get(position) else ""
    }
}