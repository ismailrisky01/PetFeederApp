package com.example.petfeederapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.replace
import com.example.petfeederapp.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}