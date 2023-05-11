package com.gmail.bakareritesh1729.spamalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.gmail.bakareritesh1729.spamalert.Adapter.FragmentSMSResultAdapter
import com.gmail.bakareritesh1729.spamalert.databinding.ActivityCheckSpamBinding
import com.gmail.bakareritesh1729.spamalert.databinding.ActivitySpamSmsresultBinding
import com.google.android.material.tabs.TabLayout

class SpamSMSResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpamSmsresultBinding

    private lateinit var adapter : FragmentSMSResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpamSmsresultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val regularSMSList = Constants.getRegularSMSList()

        adapter = FragmentSMSResultAdapter(supportFragmentManager, lifecycle)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Spam Message"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Regular Message"))

        binding.viewPager2.adapter = adapter



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })



    }


}