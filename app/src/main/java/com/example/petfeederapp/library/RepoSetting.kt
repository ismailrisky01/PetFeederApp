package com.example.petfeederapp.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petfeederapp.model.DeviceData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

object RepoSetting {
    private val firebase = FirebaseDatabase.getInstance().reference.child("Kode")
    private val firestore = FirebaseFirestore.getInstance()


    fun getdata(): LiveData<MutableList<DeviceData>> {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val mutableList = MutableLiveData<MutableList<DeviceData>>()
        firestore.collection(uid).get().addOnSuccessListener {
            val listData = mutableListOf<DeviceData>()
            for (i in it) {

                val deviceID = i.getString("id") as String
                val deviceName = i.getString("name") as String
                val device = DeviceData(deviceID, deviceName)
                listData.add(device)
            }
            mutableList.value = listData
        }
        return mutableList
    }

    fun addData(uid: String, device: DeviceData) {
        firestore.collection(uid).document().set(device).addOnSuccessListener { documentReference ->

        }
    }
}