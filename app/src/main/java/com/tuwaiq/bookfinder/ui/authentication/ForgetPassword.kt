package com.tuwaiq.bookfinder.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.bookfinder.R


class forgetPassword : Fragment() {

    private lateinit var reset: Button
    private lateinit var email: EditText
    private lateinit var arrowBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        reset = view.findViewById(R.id.btnResetPass)
        email = view.findViewById(R.id.et_email)
        arrowBack = view.findViewById(R.id.arrow_back)

        arrowBack.setOnClickListener{
            findNavController().navigate(R.id.action_forgetPassword_to_loginFragment2)

        }
        reset.setOnClickListener{
            val email: String = email.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(context, "Please enter email address. ", Toast.LENGTH_LONG).show()

            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Email sent successfully to reset your password! ",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_forgetPassword_to_loginFragment2)

                        } else {
                            Toast.makeText(
                                context,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            }
        }
    }
}