package com.example.taskthreetabwithviewpager

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.taskthreetabwithviewpager.adapters.ViewPagerAdapter
import com.example.taskthreetabwithviewpager.databinding.ActivityMainBinding
import com.example.taskthreetabwithviewpager.fragments.OneFragment
import com.example.taskthreetabwithviewpager.fragments.ThreeFragment
import com.example.taskthreetabwithviewpager.fragments.TwoFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    val numArray = arrayOf(
        "One",
        "Two",
        "Three"
    )

    val fragmentArray = arrayOf(
        OneFragment(),
        TwoFragment(),
        ThreeFragment()
    )

    private lateinit var toggle: ActionBarDrawerToggle

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            toggle = ActionBarDrawerToggle(this@MainActivity, binding.root, R.string.open, R.string.close)
            binding.root.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.clickDrawer.setOnClickListener {
            if (binding.root.isDrawerOpen(GravityCompat.START)) {
                // If the drawer is open, close it
                binding.root.closeDrawer(GravityCompat.START)
            } else {
                // If the drawer is closed, open it
                binding.root.openDrawer(GravityCompat.START)
            }
        }
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // Close the drawer
            binding.root.closeDrawer(GravityCompat.START)

            // Handle item clicks here
            when (menuItem.itemId) {
                R.id.itemShare -> {
                    Toast.makeText(this@MainActivity, "share", Toast.LENGTH_SHORT).show()
                    // Handle other item clicks
                }
                R.id.itemImages -> {
                    startActivity(Intent(this@MainActivity, ImagesActivity::class.java))
                }

                R.id.itemVideos -> {
                    startActivity(Intent(this@MainActivity, VideosActivity::class.java))
                }

                R.id.itemMedia -> {
                    startActivity(Intent(this@MainActivity, MediaActivity::class.java))
                }

                R.id.itemExit -> {
                    Toast.makeText(this@MainActivity, "exit", Toast.LENGTH_SHORT).show()
                    // Handle other item clicks
                }
                // Add more cases for other items as needed
            }

            true
        }




        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.offscreenPageLimit=2
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragmentArray)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = numArray[position]
        }.attach()


    }
    override fun onBackPressed() {
        if (binding.root.isDrawerOpen(GravityCompat.START)) {
            binding.root.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun setSignInData(userName: String, password: String) {
        binding.viewPager.setCurrentItem(2, true)
        (fragmentArray[2] as ThreeFragment).getData(userName, password)
    }

    fun signUpForm() {
        binding.viewPager.setCurrentItem(1, true)
    }

    fun setRegisterData(name: String, userName: String,  password: String) {

        (fragmentArray[0] as OneFragment).getUserDetail(name, userName, password)
        binding.viewPager.setCurrentItem(0, true)
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState()
    }


}