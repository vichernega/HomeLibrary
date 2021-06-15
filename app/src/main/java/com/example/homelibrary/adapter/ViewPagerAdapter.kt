package com.example.homelibrary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homelibrary.ui.fragment.Add.AddBookFragment
import com.example.homelibrary.ui.fragment.Add.AddReaderFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            1 -> AddReaderFragment()
            else -> AddBookFragment()
        }
    }
}