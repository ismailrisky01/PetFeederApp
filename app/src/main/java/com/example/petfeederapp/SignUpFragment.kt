package com.example.petfeederapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(), View.OnClickListener {

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
        signUp_btn.setOnClickListener(this)
        signUp_img_back.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.signUp_btn -> signUp()
            R.id.signUp_img_back -> back()
        }
    }

    private fun back() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }

    private fun signUp() {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }
}