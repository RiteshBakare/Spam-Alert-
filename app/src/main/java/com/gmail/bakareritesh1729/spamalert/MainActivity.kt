package com.gmail.bakareritesh1729.spamalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.bakareritesh1729.spamalert.InitialPackage.SplashScreenFragment
import com.gmail.bakareritesh1729.spamalert.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout,SplashScreenFragment())
            commit()
        }

    }


    override fun onRestart() {
        super.onRestart()
        finish()
    }

}