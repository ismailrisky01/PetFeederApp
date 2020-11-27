package com.example.petfeederapp.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petfeederapp.library.Repo
import com.example.petfeederapp.library.RepoSetting
import com.example.petfeederapp.model.DeviceData

class SettingViewModel : ViewModel() {
    private val repo = RepoSetting
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getDeviceData(): LiveData<MutableList<DeviceData>>{
        val mutableData = MutableLiveData<MutableList<DeviceData>>()
        repo.getdata().observeForever {
            mutableData.value = it
        }
        return mutableData
    }

    fun addDeviceData(uid:String,device: DeviceData){
        repo.addData(uid,device)
    }

}