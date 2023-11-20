package com.example.taskthreetabwithviewpager.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val NUM_TABS = 3
class ViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    val fragmentArrayList: Array<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return fragmentArrayList[0]
            1 -> return fragmentArrayList[1]
        }
        return fragmentArrayList[2]
    }
}