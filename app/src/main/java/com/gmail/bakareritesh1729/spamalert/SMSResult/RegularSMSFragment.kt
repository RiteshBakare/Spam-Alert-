package com.gmail.bakareritesh1729.spamalert.SMSResult

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bakareritesh1729.spamalert.Adapter.MessageAdapter
import com.gmail.bakareritesh1729.spamalert.Constants
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentRegularSMSBinding

class RegularSMSFragment : Fragment() {

    private lateinit var binding: FragmentRegularSMSBinding

    private var regularSMSList: ArrayList<UserData> = ArrayList()


    override fun onStart() {
        super.onStart()
        regularSMSList = Constants.getRegularSMSList()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegularSMSBinding.inflate(layoutInflater, container, false)

        regularSMSList = Constants.getRegularSMSList()!!

        Log.e("SpamSMS","the list is $regularSMSList ")

        addRegularSMSToRecyclerView()

        return binding.root
    }


    private fun addRegularSMSToRecyclerView() {

        if(regularSMSList.isNotEmpty()) {
            binding.RegularSMSRV.layoutManager =
                LinearLayoutManager(requireContext().applicationContext)

            val adapter = MessageAdapter(regularSMSList, R.color.light_blue)

            binding.RegularSMSRV.adapter = adapter
        }
    }

}