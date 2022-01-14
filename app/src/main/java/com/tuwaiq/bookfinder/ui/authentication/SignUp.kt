package com.tuwaiq.bookfinder.ui.authentication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.bookfinder.ValidationTest.Validation
import com.tuwaiq.bookfinder.R
import com.tuwaiq.bookfinder.ViewModel.MainVM
import com.tuwaiq.bookfinder.data.model.Users



class SignUp : Fragment() {
    private lateinit var username: EditText
    private lateinit var emaile: EditText
    private lateinit var password: EditText
    private lateinit var tvToLogin: TextView
    private lateinit var btnSignup: Button

    private val ref1 = FirebaseAuth.getInstance()
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignup = view.findViewById(R.id.btn_signup)
        username = view.findViewById(R.id.et_username2)
        emaile = view.findViewById(R.id.et_email2)
        password = view.findViewById(R.id.et_password2)
        tvToLogin = view.findViewById(R.id.tv_to_log_in)


        tvToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment2)
        }

        btnSignup.setOnClickListener {


            when {
                TextUtils.isEmpty(emaile.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        getString(R.string.please_enter_email),
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        getString(R.string.please_enter_password),
                        Toast.LENGTH_LONG
                    ).show()


                }
                !Validation.emil(emaile.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        getString(R.string.invalid_email),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    ref1.createUserWithEmailAndPassword(
                        emaile.text.toString().trim().toLowerCase(),
                        password.text.toString().trim()
                    ).addOnCompleteListener { register ->

                        // if the registration is successfully done
                        if (register.isSuccessful) {

                            Toast.makeText(
                                context,
                                getString(R.string.registered_successfully),
                                Toast.LENGTH_LONG
                            ).show()
                            sendUserData(
                                username.text.toString(),
                                emaile.text.toString()
                            )

                        } else {
                            // if the registration is not successful then show error massage
                            Toast.makeText(
                                context,
                              getString(R.string.try_again),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun sendUserData(username: String, emaile: String) {

        val user = Users(username, emaile)
        saveUserFireStore(user)

    }

    private fun saveUserFireStore(user: Users) {// = CoroutineScope(Dispatchers.IO).launch {
        vm.saveUserData(user)
            findNavController().navigate(R.id.action_signUpFragment2_to_bookFragment)
        }
    }
