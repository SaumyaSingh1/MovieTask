package com.example.movietask

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(val fragments: ArrayList<Fragment>, val fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Top Rated Movies"
            1 -> return "Latest Movies"
            else -> return ""
        }
    }

}