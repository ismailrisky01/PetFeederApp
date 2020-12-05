package com.example.petfeederapp.library

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.petfeederapp.R
import com.example.petfeederapp.model.DeviceData
import com.example.petfeederapp.ui.setting.SettingFragment
import com.example.petfeederapp.ui.setting.SettingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_scanner_qr.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerQr : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private val settingMainView by lazy {
        ViewModelProvider(this).get(SettingViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_qr)
        setScannerProperties()
    }

    private fun setScannerProperties() {
        qrCodeScanner.setFormats(listOf(BarcodeFormat.QR_CODE))
        qrCodeScanner.setAutoFocus(true)
        qrCodeScanner.setLaserColor(R.color.design_default_color_error)
        qrCodeScanner.setMaskColor(R.color.design_default_color_primary)
        if (Build.MANUFACTURER.equals("REALME", ignoreCase = true))
            qrCodeScanner.setAspectTolerance(0.5f)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    112
                )
                return
            }
        }
        qrCodeScanner.startCamera()
        qrCodeScanner.setResultHandler(this)
    }

    override fun handleResult(p0: Result?) {
        if (p0 != null) {
            Toast.makeText(this, p0.text, Toast.LENGTH_LONG).show()
            val user = FirebaseAuth.getInstance().currentUser!!.uid
            settingMainView.addDeviceData(user, DeviceData(p0.text,"Name Not Set"))
            finish()

            
        }
    }

    override fun onPause() {
        super.onPause()
        qrCodeScanner.stopCamera()
    }
}