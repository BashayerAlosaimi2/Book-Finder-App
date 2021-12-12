package com.tuwaiq.bookfinder.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.tuwaiq.bookfinder.R

class Splash : Fragment() {

    private lateinit var logo: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logo = view.findViewById(R.id.ic_logo)
        // Setting up two animations with their respective time
        logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_in))
        Handler().postDelayed({
            logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_out))
            Handler().postDelayed({
                logo.visibility = View.GONE

                findNavController().navigate(R.id.loginFragment2)
                /*    startActivity(Intent(this, LoginFragment::class.java))
                    finish()*/
            },500)
        },1500)

    }
}