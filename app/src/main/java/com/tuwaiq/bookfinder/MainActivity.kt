package com.tuwaiq.bookfinder

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tuwaiq.bookfinder.Constants.Companion.DARKMOOD
import com.tuwaiq.bookfinder.Constants.Companion.LOCALE
import com.tuwaiq.bookfinder.Constants.Companion.PREFERENCE
import com.tuwaiq.bookfinder.notification.NotificationRepo
import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationRepo().myNotification(this)
        preferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

        pef(preferences)
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
                R.id.forgetPassword -> {
                    bottomNavigation.visibility = View.GONE
                }
                else -> {
                    bottomNavigation.visibility = View.VISIBLE
                }
            }
        }

    }


    private fun setLocate(activity: Activity, Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

    }



    fun pef(preferences: SharedPreferences) {

        if (preferences.getBoolean(DARKMOOD, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (preferences.getString(LOCALE, "en") == "ar") {
            setLocate(this, "ar")
        } else {
            setLocate(this, "en")
        }


    }

}

