package com.tuwaiq.bookfinder.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tuwaiq.bookfinder.R
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.delay


class Splash : Fragment() {

   // private lateinit var logo: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        logoCompose.setContent {
            MaterialTheme {
                AnimationSplash(findNavController()) //navigate(R.id.action_splash_to_loginFragment2)

            }

        }


        /*  logo = view.findViewById(R.id.ic_logo)
          // Setting up two animations with their respective time
          logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_in))
          Handler().postDelayed({
              logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_out))
              Handler().postDelayed({
                  logo.visibility = View.GONE
                  findNavController().navigate(R.id.action_splash_to_loginFragment2)
                  //findNavController().popBackStack()

              },0)
          },0)*/

    }

    @Composable
    fun AnimationSplash(nav: NavController) {
        var startAnim by remember { mutableStateOf(false) }
        val scaleAnim by animateSizeAsState(
            targetValue = if (startAnim) Size(100F, 100F) else Size(10F, 10F),
            animationSpec = tween(durationMillis = 2000)
        )
        val rotateAnim by animateFloatAsState(
            targetValue = if (startAnim) 360F else 0F,
            animationSpec = tween(durationMillis = 2000)
        )

        LaunchedEffect(key1 = true) {
            startAnim = true
            delay(2000)
            nav.navigate(R.id.action_splash_to_loginFragment2)
        }

        AnimateSplash(scaleAnim,rotateAnim)
    }

    @Composable
    private fun AnimateSplash(scaleAnim: Size, rotateAnim: Float) {
        Column(
            modifier= Modifier.fillMaxSize().background(if ( isSystemInDarkTheme()) Color.Black else Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(modifier = Modifier
                .size(scaleAnim.width.dp, scaleAnim.height.dp)
                .rotate(rotateAnim),painter = painterResource(id = R.drawable.books_logo),
                contentDescription = "logo"

            )
        }

    }

}