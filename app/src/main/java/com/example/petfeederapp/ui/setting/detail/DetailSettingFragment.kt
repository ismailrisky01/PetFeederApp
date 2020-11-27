package com.example.petfeederapp.ui.setting.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.petfeederapp.R
import com.example.petfeederapp.library.Repo
import com.example.petfeederapp.model.Device
import com.example.petfeederapp.model.Jam
import com.example.petfeederapp.ui.setting.SettingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_detail_setting.*
import kotlinx.coroutines.GlobalScope
import java.lang.ArithmeticException


class DetailSettingFragment : Fragment(), View.OnClickListener {
    private val DetailMainView by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swt1.setOnClickListener(this)
        swt2.setOnClickListener(this)
        swt3.setOnClickListener(this)
        swt4.setOnClickListener(this)
        swt5.setOnClickListener(this)
        swt6.setOnClickListener(this)
        btnSetNow.setOnClickListener(this)

        GlobalScope.apply {
            val id = arguments?.getString("id").toString()
            DetailMainView.getDeviceDataRealtime(id).observe(viewLifecycleOwner, Observer { data ->
                updateUI(data[0])
            })
        }
       }

    fun updateUI(device: Device) {
        if (device.id == "") {
            Detail_Container.visibility = View.GONE
        } else {
            txtId.text = device.id
            txtDate1.text = device.jam.jam1
            txtDate2.text = device.jam.jam2
            txtDate3.text = device.jam.jam3
            txtDate4.text = device.jam.jam4
            txtDate5.text = device.jam.jam5
            txtDate6.text = device.jam.jam6
            swt1.isChecked = device.jam.statusjam1
            swt2.isChecked = device.jam.statusjam2
            swt3.isChecked = device.jam.statusjam3
            swt4.isChecked = device.jam.statusjam4
            swt5.isChecked = device.jam.statusjam5
            swt6.isChecked = device.jam.statusjam6
        }
    }


    fun update() {
        val id = txtId.text.toString()
        val jam1 = txtDate1.text.toString()
        val jam2 = txtDate2.text.toString()
        val jam3 = txtDate3.text.toString()
        val jam4 = txtDate4.text.toString()
        val jam5 = txtDate5.text.toString()
        val jam6 = txtDate6.text.toString()
        val jamNow = false
        val statusjam1 = swt1.showText
        val statusjam2 = swt2.showText
        val statusjam3 = swt3.showText
        val statusjam4 = swt4.showText
        val statusjam5 = swt5.showText
        val statusjam6 = swt6.showText
        val data = Device(
            id,
            Jam(
                jam1,
                jam2,
                jam3,
                jam4,
                jam5,
                jam6,
                jamNow,
                statusjam1,
                statusjam2,
                statusjam3,
                statusjam4,
                statusjam5,
                statusjam6
            )
        )
        DetailMainView
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSetNow -> DetailMainView.updateSetNow(txtId.text.toString())
        }
    }
}