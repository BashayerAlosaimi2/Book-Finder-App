package com.tuwaiq.bookfinder.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.data.model.Users



class SignUpFragment : Fragment() {
    private lateinit var username: EditText
    private lateinit var emaile: EditText
    private lateinit var password: EditText
    private lateinit var tvlogin: TextView
    private lateinit var btnSignup: Button
    private  var db  = FirebaseFirestore.getInstance()
    private val ref1 = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnSignup = view.findViewById(R.id.btn_signup)
        username = view.findViewById(R.id.et_username2)
        emaile = view.findViewById(R.id.et_email2)
        password = view.findViewById(R.id.et_password2)
        tvlogin =view.findViewById(R.id.tv_log_in)
        btnSignup.setOnClickListener {


            tvlogin.setOnClickListener {
                findNavController().navigate(R.id.loginFragment2)
            }
            when {
                TextUtils.isEmpty(emaile.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Password",
                        Toast.LENGTH_LONG
                    ).show()


                }
                else -> {
                    ref1.createUserWithEmailAndPassword(
                        emaile.text.toString().trim().lowercase(),
                        password.text.toString().trim()
                    ).addOnCompleteListener { register ->

                        // if the registration is successfully done
                        if (register.isSuccessful) {

                            Toast.makeText(
                                context,
                                "You were registered successfully",
                                Toast.LENGTH_LONG
                            ).show()
                            saveData(
                                username.text.toString(),
                                emaile.text.toString()
                            )

                        } else {
                            // if the registration is not successful then show error massage
                            Toast.makeText(
                                context,
                                register.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun saveData(username: String, emaile: String, ) {

        val user = Users(username, emaile)
        saveUserFireStore(user)

    }

    private fun saveUserFireStore(user: Users){// = CoroutineScope(Dispatchers.IO).launch {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        try {
            db.collection("Users").document("$uid").set(user).addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data.", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_signUpFragment2_to_bookFragment)

            }

        } catch (e: Exception) {
            //withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
          //  }
        }
    }
}

