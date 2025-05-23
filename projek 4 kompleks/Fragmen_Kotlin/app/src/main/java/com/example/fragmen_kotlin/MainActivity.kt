package com.example.fragmen_kotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmen_kotlin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private val fragments = listOf(FragmenKomik(), Profile(), Setting())

    companion object {
        private const val THEME_PREFS = "theme_prefs"
        private const val THEME_KEY = "theme_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Load theme before setting content view
        sharedPreferences = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
        val savedTheme = sharedPreferences.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(savedTheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        viewPager = binding.viewPager

        pagerAdapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = pagerAdapter

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.menu)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bNav.selectedItemId = R.id.comic
                    1 -> binding.bNav.selectedItemId = R.id.music
                    2 -> binding.bNav.selectedItemId = R.id.history
                }
            }
        })

        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.comic -> viewPager.currentItem = 0
                R.id.music -> viewPager.currentItem = 1
                R.id.history -> viewPager.currentItem = 2
                else -> {}
            }
            true
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> viewPager.currentItem = 0
                R.id.nav_profile -> viewPager.currentItem = 1
                R.id.nav_settings -> viewPager.currentItem = 2
                R.id.nav_about -> showAboutDialog()
                R.id.nav_logout -> logout()
                R.id.nav_light_mode -> toggleTheme(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.nav_dark_mode -> toggleTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun toggleTheme(mode: Int) {
        // Save theme preference
        sharedPreferences.edit() { putInt(THEME_KEY, mode) }

        // Apply theme
        AppCompatDelegate.setDefaultNightMode(mode)

        // Show confirmation
        val themeName = if (mode == AppCompatDelegate.MODE_NIGHT_YES) "Dark" else "Light"
        Toast.makeText(this, "$themeName mode applied", Toast.LENGTH_SHORT).show()
    }

    private fun showAboutDialog() {
        Toast.makeText(this, "Tentang", Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navigationView)
        return true
    }
}