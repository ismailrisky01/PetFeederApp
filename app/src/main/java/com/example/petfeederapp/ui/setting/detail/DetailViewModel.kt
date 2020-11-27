package com.example.petfeederapp.ui.setting.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petfeederapp.library.BaseActivity
import com.example.petfeederapp.library.Repo
import com.example.petfeederapp.model.Device
import com.example.petfeederapp.model.DeviceData

class DetailViewModel:ViewModel() {
    private val repo = Repo
    fun getDeviceDataRealtime(id:String): LiveData<MutableList<Device>> {

        val mutableData = MutableLiveData<MutableList<Device>>()
        repo.getDataRealtime(id).observeForever {
            mutableData.value = it
        }
        return mutableData
    }
    fun update(device: Device){
        repo.updateData(device)
    }
    fun updateSetNow(id: String){
        repo.updateStatusNow(id)
    }

}