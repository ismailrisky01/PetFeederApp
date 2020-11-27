package com.example.petfeederapp.library

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petfeederapp.model.Device
import com.example.petfeederapp.model.DeviceData
import com.example.petfeederapp.model.Jam
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

object Repo {
    private val firebase = FirebaseDatabase.getInstance().reference.child("Kode")
    private val firestore = FirebaseFirestore.getInstance()




    fun getDataRealtime(id:String): LiveData<MutableList<Device>> {
        val mutableList = MutableLiveData<MutableList<Device>>()
        firebase.child(id).addValueEventListener(object : ValueEventListener {
            val listData = mutableListOf<Device>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val device = snapshot.getValue(Device::class.java) as Device
                    val id = device.id
                    val jam = device.jam
                    val data = Device(id,jam)
                    listData.add(data)
                    mutableList.value = listData
                }else{
                    val data = Device("", Jam("","","","","","",false,false,false,false,false,false,false))
                    listData.add(data)
                    mutableList.value = listData
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MSG", error.message)
            }

        })
        return mutableList
    }
    fun updateData(data: Device){

        val applesQuery: Query = firebase.orderByChild("id").equalTo(data.id)
        applesQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (appleSnapshot in snapshot.children){
                    appleSnapshot.ref.child("jam1").setValue(data.jam.jam1)
                    appleSnapshot.ref.child("jam2").setValue(data.jam.jam2)
                    appleSnapshot.ref.child("jam3").setValue(data.jam.jam3)
                    appleSnapshot.ref.child("jam4").setValue(data.jam.jam4)
                    appleSnapshot.ref.child("jam5").setValue(data.jam.jam5)
                    appleSnapshot.ref.child("jam6").setValue(data.jam.jam6)
                    appleSnapshot.ref.child("jamNow").setValue(data.jam.jamNow)
                    appleSnapshot.ref.child("statusjam1").setValue(data.jam.statusjam1)
                    appleSnapshot.ref.child("statusjam2").setValue(data.jam.statusjam1)
                    appleSnapshot.ref.child("statusjam3").setValue(data.jam.statusjam1)
                    appleSnapshot.ref.child("statusjam4").setValue(data.jam.statusjam1)
                    appleSnapshot.ref.child("statusjam5").setValue(data.jam.statusjam1)
                    appleSnapshot.ref.child("statusjam6").setValue(data.jam.statusjam1)


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun updateStatusNow(id: String){
        val applesQuery: Query = firebase.orderByChild("id").equalTo(id)
        applesQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (appleSnapshot in snapshot.children){
                    appleSnapshot.ref.child("jam").child("jamNow").setValue(true)



                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

}
}