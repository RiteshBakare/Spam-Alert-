package com.gmail.bakareritesh1729.spamalert.InitialPackage

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.gmail.bakareritesh1729.spamalert.HomeScreen
import com.gmail.bakareritesh1729.spamalert.R


class PermissionFragment : Fragment() {

    private var isReadSMSPermissionGranted = false
    private var isReceiveSMSPermissionGranted = false
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
                isReadSMSPermissionGranted =
                    permission[android.Manifest.permission.READ_SMS] ?: isReadSMSPermissionGranted
                isReceiveSMSPermissionGranted = permission[android.Manifest.permission.RECEIVE_SMS]
                    ?: isReceiveSMSPermissionGranted
            }

        getUserPermissions()



        startActivity(Intent(context,HomeScreen::class.java))

        return inflater.inflate(R.layout.fragment_permission, container, false)
    }

    private fun getUserPermissions() {

        isReadSMSPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext().applicationContext,
            android.Manifest.permission.READ_SMS
        ) == PackageManager.PERMISSION_GRANTED
        isReceiveSMSPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext().applicationContext,
            android.Manifest.permission.RECEIVE_SMS
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if (!isReadSMSPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.READ_SMS)
        }
        if (!isReceiveSMSPermissionGranted) {
            permissionRequest.add(android.Manifest.permission.RECEIVE_SMS)
        }
        if (permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }

    }

}