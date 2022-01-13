package com.tuwaiq.bookfinder

import android.content.Context
import android.content.SharedPreferences
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
import com.roula.kidslearning.util.Validation
import com.tuwaiq.bookfinder.Constants.Companion.CHECKBOX
import com.tuwaiq.bookfinder.Constants.Companion.EMAIL
import com.tuwaiq.bookfinder.Constants.Companion.PASSWORD
import com.tuwaiq.bookfinder.Constants.Companion.PREFERENCE
import com.tuwaiq.bookfinder.ui.MainVM



class LoginFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var tvSignup: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var btnLogin: Button
    private lateinit var rememberMe: CheckBox
    private lateinit var preferences: SharedPreferences

    var isRemember = false
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
        forgetPassword = view.findViewById(R.id.tv_forget_password)
        rememberMe = view.findViewById(R.id.checkBox_remember)

        forgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_forgetPassword)
        }

        tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_signUpFragment2)
        }
        btnLogin.setOnClickListener {

            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
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
                !Validation.emil(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        getString(R.string.invalid_email),
                        Toast.LENGTH_LONG
                    ).show()

                }


                else -> {
                    val email: String = email.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                vm.userData(viewLifecycleOwner)

                                Toast.makeText(
                                    context,
                                   getString(R.string.welcome),
                                    Toast.LENGTH_LONG
                                ).show()

                                editor.putString(EMAIL, email)
                                    .putString(PASSWORD, password)
                                    .putBoolean(CHECKBOX, rememberMe.isChecked)
                                    .apply()

                                findNavController().navigate(R.id.action_loginFragment2_to_bookFragment)

                            } else {
                                // if the registreation is not succsesful then show error massage
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
}