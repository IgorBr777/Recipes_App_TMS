package com.example.recipes.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipes.R
import com.example.recipes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        viewModel.isNetworkAvailable()
        viewModel.network.observe(this) { isConnected ->
            if (isConnected) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.internet_connected),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.there_is_no_internet_connection_check_connection),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModel.isDarkTheme()
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.exit_navigation_menu, menu)

        menuInflater.inflate(R.menu.switch_dark_theme_menu, menu)
        val menuItem = menu?.findItem(R.id.action_switch)
        menuItem?.let {
            val switch = it.actionView?.findViewById<Switch>(R.id.action_switch)
            if (switch != null) {
                switch.isChecked = viewModel.darkThemeEnabled.value ?: false
            }
            switch?.setOnCheckedChangeListener { _, isChecked->
                viewModel.setDarkTheme(isChecked)
            }
            viewModel.darkThemeEnabled.observe(this) { darkThemeEnable ->
                if (darkThemeEnable) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_item -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}