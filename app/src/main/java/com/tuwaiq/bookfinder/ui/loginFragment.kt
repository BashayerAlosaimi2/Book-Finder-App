package com.tuwaiq.bookfinder

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.ui.MainVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




class LoginFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var tvSignup: TextView
    private lateinit var btnLogin: Button
    private lateinit var rememberMe: CheckBox
    private lateinit var preferences: SharedPreferences

    var isRemember = false
    private val vm by lazy {
        ViewModelProvider(requireActivity())[MainVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = requireContext().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()


        isRemember = preferences.getBoolean(CHECKBOX, false)
        if (isRemember) {
            findNavController().navigate(R.id.action_loginFragment2_to_bookFragment)

        }


        email = view.findViewById(R.id.et_email)
        password = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)
        tvSignup = view.findViewById(R.id.tv_sign_up)
        rememberMe = view.findViewById(R.id.checkBox_remember)


        tvSignup.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment2)
        }
        btnLogin.setOnClickListener {

            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
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
                    val email: String = email.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                //firebase register user
                                Toast.makeText(
                                    context,
                                    "Welcome",
                                    Toast.LENGTH_LONG
                                ).show()
                                // get user data to save it in user profile

                                editor.putString(EMAIL, email)
                                //.putString(NAME, userDara.username)
                                    .putString(PASSWORD, password)
                                    .putBoolean(CHECKBOX, rememberMe.isChecked)
                                    .apply()

                                Toast.makeText(context, "login Saved!", Toast.LENGTH_LONG).show()


                                findNavController().navigate(R.id.action_loginFragment2_to_bookFragment)
                                // findNavController().popBackStack()

                            } else {
                                // if the registreation is not succsesful then show error massage
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                }


            }

        }

    }


}