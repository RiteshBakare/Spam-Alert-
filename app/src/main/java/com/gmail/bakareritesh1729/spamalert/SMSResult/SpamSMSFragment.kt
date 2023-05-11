package com.gmail.bakareritesh1729.spamalert.SMSResult

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bakareritesh1729.spamalert.Adapter.MessageAdapter
import com.gmail.bakareritesh1729.spamalert.Constants
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentSpamSMSBinding


class SpamSMSFragment : Fragment() {

    private lateinit var binding: FragmentSpamSMSBinding


    private var spamSMSList: ArrayList<UserData> = ArrayList()

    override fun onStart() {
        super.onStart()
        spamSMSList = Constants.getSpamSMSList()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpamSMSBinding.inflate(layoutInflater, container, false)

        spamSMSList = Constants.getSpamSMSList()!!

        addSpamSMSToRecyclerView()


        return binding.root
    }

    private fun addSpamSMSToRecyclerView() {

        if (spamSMSList.isNotEmpty()) {

            binding.SpamSMSRV.layoutManager =
                LinearLayoutManager(requireContext().applicationContext)

            val adapter = MessageAdapter(spamSMSList, R.color.light_red)

            binding.SpamSMSRV.adapter = adapter
        }

    }

}