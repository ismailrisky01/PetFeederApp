package com.example.petfeederapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petfeederapp.ui.DashboardActivity
import com.example.petfeederapp.ui.auth.AuthActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
//        val user =FirebaseAuth.getInstance().currentUser
//        if (user != null) {
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//        } else {
//            val intent = Intent(this, AuthActivity::class.java)
//            startActivity(intent)
//        }
    }
}