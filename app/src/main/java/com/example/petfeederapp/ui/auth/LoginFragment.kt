package com.example.petfeederapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.petfeederapp.R
import com.example.petfeederapp.library.Google
import com.example.petfeederapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Google() {

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

        btnLogin.setOnClickListener {
            login()
        }
        btnLoginGoogle.setOnClickListener {
            loginGoogle()
        }
        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }

    }


    fun login() {
        val email = edtUsername.text.toString()
        val password = edtPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {

        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email!!, password)
            .addOnCompleteListener {

                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(requireContext(), "Succesfully Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Main", "Failed Login: ${it.message}")
                Toast.makeText(requireContext(), "Email/Password incorrect", Toast.LENGTH_SHORT)
                    .show()


            }
    }

    fun loginGoogle() {
        signIn()
    }
}
