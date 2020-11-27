package com.example.petfeederapp.library

import android.util.Log
import com.example.petfeederapp.model.DeviceData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Database {
    val user = FirebaseAuth.getInstance().currentUser

    private val database = FirebaseFirestore.getInstance()

    fun pushData(uid:String,idAlat:String){
        database.collection(uid).document().set(DeviceData(idAlat)).addOnSuccessListener { documentReference ->

        }
    }
}