
package com.faridrama123.proyekmovie.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.faridrama123.proyekmovie.databinding.ActivityHomeBinding
import com.faridrama123.proyekmovie.ui.home.SectionsPagerAdapter

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        if (supportActionBar != null) {
            supportActionBar!!.elevation = 0f
        }
    }
}