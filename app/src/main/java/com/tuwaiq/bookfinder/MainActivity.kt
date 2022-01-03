package com.tuwaiq.bookfinder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tuwaiq.bookfinder.notification.NotificationRepo
import java.util.*

const val PREFERENCE = "PREFERENCE"
const val NAME = "NAME"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val CHECKBOX = "CHECKBOX"
const val DARKMOOD = "DARKMOOD"
const val LOCALE = "LOCALE"


class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationRepo().myNotification(this)
        preferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

        pef(preferences)
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

    private fun setLocate(activity: Activity, Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

    }
    fun pef(preferences: SharedPreferences){

        //check the dark mood user option
        if (preferences.getBoolean(DARKMOOD, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        //check localization user option
        if (preferences.getString(LOCALE, "en") == "ar") {
            setLocate(this, "ar")
        } else {
            setLocate(this, "en")
        }


    }
    //localization Fun
    fun setLocale(activity: MainActivity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        with(resources) {
            config.setLocale(locale)
            updateConfiguration(config, displayMetrics)

           startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
    }
}

