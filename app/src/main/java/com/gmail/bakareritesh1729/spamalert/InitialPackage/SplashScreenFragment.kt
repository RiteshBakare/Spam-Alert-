package com.gmail.bakareritesh1729.spamalert.InitialPackage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.bakareritesh1729.spamalert.HomeScreen
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentSplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var splashScreenBinding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        splashScreenBinding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        splashScreenBinding.LogoAnimation.playAnimation()

        val handle = Handler(Looper.getMainLooper()).postDelayed({
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.FrameLayout, PermissionFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }, 2500)


        return splashScreenBinding.root
    }


}