package com.tuwaiq.bookfinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
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

        LaunchedEffect(key1 = true) {
            //startAnim = true
            delay(4000)
            nav.navigate(R.id.action_splash_to_loginFragment2)
        }
        Loader()
    }

    @Composable
    fun Loader() {
        val animationSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.orang_book_logo))
        LottieAnimation(
            animationSpec,
            modifier = Modifier.requiredSize(500.dp)
        )
    }


}