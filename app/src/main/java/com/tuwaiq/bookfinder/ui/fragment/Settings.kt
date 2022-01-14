package com.tuwaiq.bookfinder.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.bookfinder.*
import com.tuwaiq.bookfinder.Util.Constants.Companion.DARKMOOD
import com.tuwaiq.bookfinder.Util.Constants.Companion.EMAIL
import com.tuwaiq.bookfinder.Util.Constants.Companion.LOCALE
import com.tuwaiq.bookfinder.Util.Constants.Companion.NAME
import com.tuwaiq.bookfinder.Util.Constants.Companion.PREFERENCE
import com.tuwaiq.bookfinder.ViewModel.MainVM
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@DelicateCoroutinesApi
class Settings : Fragment() {

    private lateinit var userNameET: EditText
    private lateinit var emailTV: TextView
    private lateinit var preferences: SharedPreferences
    private lateinit var logOutTV: TextView
    private lateinit var engLangTV: TextView
    private lateinit var arLangTV: TextView
    private lateinit var editIconIV: ImageView
    private lateinit var doneIconIV: ImageView

    private val vm by lazy {
        ViewModelProvider(this)[MainVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("WrongConstant", "UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameET = view.findViewById(R.id.et_name)
        emailTV = view.findViewById(R.id.tv_email)
        logOutTV = view.findViewById(R.id.profile_signout)
        engLangTV = view.findViewById(R.id.tvEngLang)
        arLangTV = view.findViewById(R.id.tvArLang)
        editIconIV = view.findViewById(R.id.editIcon)
        doneIconIV = view.findViewById(R.id.doneIcon)
        doneIconIV.isVisible = false
        userNameET.isEnabled = false

        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)

        preferences = requireContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

        val pName = preferences.getString(NAME, "")
        val pEmail = preferences.getString(EMAIL, "")

        emailTV.text = pEmail
        userNameET.setText(pName)

        var cliclkBtn = false

        editIconIV.setOnClickListener {
            if (!cliclkBtn) {
                GlobalScope.launch {
                    editIconIV.startAnimation(scaleUp)
                    delay(500)
                    editIconIV.setImageResource(R.drawable.ic_baseline_done_24)
                }
                userNameET.isEnabled = true
                cliclkBtn = true
            } else {
                GlobalScope.launch {
                    editIconIV.startAnimation(scaleUp)
                    delay(500)
                    editIconIV.setImageResource(R.drawable.ic_edit)
                }
                userNameET.isEnabled = false
                vm.updateUserName(userNameET.text.toString())
                cliclkBtn = false

            }

        }

        val toggle: Switch = view.findViewById(R.id.switchTheme)
        toggle.isChecked = preferences.getBoolean(DARKMOOD, false)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                preferences.edit().putBoolean(DARKMOOD, true).apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                preferences.edit().putBoolean(DARKMOOD, false).apply()
            }
        }
        val mainActivity = requireActivity() as MainActivity


        engLangTV.setOnClickListener {
            GlobalScope.launch {
                engLangTV.startAnimation(scaleUp)
                delay(300)
            }
            setLocale("en")
            preferences.edit().putString(LOCALE, "en").apply()
            refreshCurrentFragment()

        }
        arLangTV.setOnClickListener {
            GlobalScope.launch {
                arLangTV.startAnimation(scaleUp)
                delay(300)
            }
            setLocale("ar")
            preferences.edit().putString(LOCALE, "ar").apply()
            refreshCurrentFragment()

        }


        logOutTV.setOnClickListener {
            GlobalScope.launch {
                logOutTV.startAnimation(scaleUp)
                delay(400)

            }

            preferences.edit().clear().apply()
            mainActivity.pef(preferences)
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)

        }
    }

    private fun refreshCurrentFragment() {
        val id = findNavController().currentDestination?.id
        findNavController().navigateUp()
        findNavController().navigate(id!!)
    }

    fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        with(resources) {
            config.setLocale(locale)
            updateConfiguration(config, displayMetrics)
            recreate(context as Activity)

        }
    }
}