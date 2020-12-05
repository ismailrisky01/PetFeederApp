package com.example.petfeederapp.ui.setting.detail

import android.app.TimePickerDialog
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
import java.text.SimpleDateFormat
import java.util.*


class DetailSettingFragment : FunSetting(), View.OnClickListener {
    var kodeID:String=""
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
        setUp()
Observe()

    }
fun Observe(){
    GlobalScope.apply {
        kodeID = arguments?.getString("id").toString()
        DetailMainView.getDeviceDataRealtime(kodeID).observe(viewLifecycleOwner, Observer { data ->
            updateUI(data[0])
        })
    }
}

    fun setUp() {
        swt1.setOnClickListener(this)
        swt2.setOnClickListener(this)
        swt3.setOnClickListener(this)
        swt4.setOnClickListener(this)
        swt5.setOnClickListener(this)
        swt6.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        txtDate1.setOnClickListener(this)
        btnSetNow.setOnClickListener(this)
    }

    fun updateUI(device: Device) {
        if (device.id == "") {
            Detail_Container.visibility = View.GONE
            Detail_Container_empty.visibility=View.VISIBLE
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



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSetNow -> DetailMainView.updateSetNow(txtId.text.toString())
            R.id.txtDate1-> setText1()
            R.id.txtDate2->setText2()
            R.id.txtDate3->setText3()
            R.id.txtDate4->setText4()
            R.id.txtDate5->setText5()
            R.id.txtDate6->setText6()
            R.id.swt1->setSwitch1()
        }
    }

    fun setSwitch1(){
        FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
            .child("statusjam1").setValue(true)
    }

    fun setText1(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate1.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam1").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
    fun setText2(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate2.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam2").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
    fun setText3(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate3.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam3").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
    fun setText4(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate4.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam4").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
    fun setText5(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate5.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam5").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
    fun setText6(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm").format(cal.time)
            txtDate6.text = SimpleDateFormat("HH:mm").format(cal.time)
            FirebaseDatabase.getInstance().reference.child("Kode").child(kodeID).child("jam")
                .child("jam6").setValue(time)
            Observe()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
}