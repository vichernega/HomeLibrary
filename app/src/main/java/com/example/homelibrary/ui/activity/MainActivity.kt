package com.example.homelibrary.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.homelibrary.R
import com.example.homelibrary.adapter.ViewPagerAdapter
import com.example.homelibrary.databinding.ActivityMainBinding
import com.example.homelibrary.utilit.APP_ACTIVITY
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initDrawer()
        showAddElements()
        setOnNavigationItemClick()
        APP_ACTIVITY = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // side menu initialization
    private fun initDrawer(){
        val drawer = binding.drawer
        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)

        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // on menu icon click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                binding.drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // on side menu items click
    private fun setOnNavigationItemClick(){
        binding.navView.setNavigationItemSelectedListener { item: MenuItem ->
            binding.drawer.closeDrawers()
            when (item.itemId){
                R.id.add -> {
                    setToolbarTitle("Add")
                    showAddElements()
                    true
                }
                R.id.books -> {
                    setToolbarTitle("Books")
                    true
                }
                R.id.readers -> {
                    setToolbarTitle("Readers")
                    true
                }
                R.id.authors_3_more_books -> {
                    setToolbarTitle("Authors with more than 3 books")
                    true
                }
                R.id.reread_3_more_times -> {
                    setToolbarTitle("Rereading more than 3 times")
                    true
                }

                else -> true
            }
        }
    }

    // make Add elements visible (viewPager for add book/reader fragments)
    private fun showAddElements(){
        binding.viewPager.visibility = View.VISIBLE
        initViewPagerAdapter()
    }

    // hide Add elements (viewPager for add book/reader fragments)
    private fun hideAddElements(){
        binding.viewPager.visibility = View.GONE
    }

    // init viewPager, add titles
    private fun initViewPagerAdapter(){
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position){
                0 -> tab.text = "Book"
                1 -> tab.text = "Reader"
            }
        }.attach()
    }

    private fun setToolbarTitle(title: String){
        binding.toolbar.title = title
    }

}