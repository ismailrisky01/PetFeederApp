package com.example.petfeederapp.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petfeederapp.R
import com.example.petfeederapp.library.Google
import com.example.petfeederapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Google() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogout.setOnClickListener {
            signOut()
            val i = Intent(requireContext(),MainActivity::class.java)
            startActivity(i)

        }
    }

}