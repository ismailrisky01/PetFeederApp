package com.example.petfeederapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.petfeederapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn_btn.setOnClickListener(this)
        signIn_img_back.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.signIn_img_back->back()
            R.id.signIn_btn->login()
        }
    }
    fun login() {
        val email = edtUsername.text.toString()
        val password = edtPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Fill empty ", Toast.LENGTH_SHORT).show()
        }
        else{
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
    }
    fun back(){
        findNavController().navigate(R.id.action_signInFragment_to_loginFragment)
    }


}