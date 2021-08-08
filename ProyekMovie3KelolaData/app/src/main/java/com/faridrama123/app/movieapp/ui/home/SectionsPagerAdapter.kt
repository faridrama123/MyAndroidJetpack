package com.faridrama123.app.movieapp.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.faridrama123.app.movieapp.R
import com.faridrama123.app.movieapp.ui.movie.MovieFragment
import com.faridrama123.app.movieapp.ui.moviefavorites.MovieFavoritesFragment
import com.faridrama123.app.movieapp.ui.tvshow.TVShowFragment
import com.faridrama123.app.movieapp.ui.tvshowfavorites.TVShowFavoritesFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab1,R.string.tab2,R.string.tab3,R.string.tab4)
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MovieFragment()
                1 -> TVShowFragment()
                2 -> MovieFavoritesFragment()
                3 -> TVShowFavoritesFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 4

}