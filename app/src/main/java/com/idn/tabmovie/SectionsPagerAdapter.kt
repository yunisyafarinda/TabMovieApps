package com.idn.tabmovie.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.idn.tabmovie.R
import com.idn.tabmovie.fragment.TvFragment
import com.idn.tabmovie.fragment.MovieFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return MovieFragment()
            1 -> return TvFragment()
        }
        return MovieFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0){
            return context.getString(R.string.tab_movie)
        }
        else if (position == 1){
            return context.getString(R.string.tab_tv)
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}