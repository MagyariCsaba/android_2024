package com.tasty.recipesapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "onCreate: MainActivity created.")

        // Retrieve message from intent
        //val message = intent.getStringExtra("message")
        //binding.messageTextView.text = message ?: "No message received."

        // Set up BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardFragment -> {
                    navigateToFragment(DashboardFragment())
                    true
                }
                R.id.recipesFragment -> {
                    navigateToFragment(RecipesFragment())
                    true
                }
                R.id.profileFragment -> {
                    navigateToFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Load the default fragment on start
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.dashboardFragment
        }
    }



    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    // Handle menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboardFragment -> {
                navigateToFragment(DashboardFragment())
                true
            }
            R.id.recipesFragment -> {
                navigateToFragment(RecipesFragment())
                true
            }
            R.id.profileFragment -> {
                navigateToFragment(ProfileFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Helper method to navigate to a fragment
    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: MainActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MainActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: MainActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: MainActivity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MainActivity destroyed.")
    }
}