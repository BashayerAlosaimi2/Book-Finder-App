package com.tuwaiq.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomnavigation)
        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splash -> {
                    bottomNavigation.visibility = View.GONE
                }
                R.id.loginFragment2 -> {
                    bottomNavigation.visibility = View.GONE
                }
                R.id.signUpFragment2 -> {
                    bottomNavigation.visibility = View.GONE
                }
                else -> {
                    bottomNavigation.visibility = View.VISIBLE
                }
            }
        }
    }
}