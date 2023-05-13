package com.gmail.bakareritesh1729.spamalert.SMSResult

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bakareritesh1729.spamalert.Adapter.MessageAdapter
import com.gmail.bakareritesh1729.spamalert.Model.Constants
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentBankMessageBinding


class BankMessageFragment : Fragment() {

    private lateinit var binding: FragmentBankMessageBinding

    private var bankSMSList : ArrayList<UserData> = ArrayList()

    override fun onStart() {
        super.onStart()
        bankSMSList = Constants.getBankSMSList()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankMessageBinding.inflate(layoutInflater,container,false)

        bankSMSList = Constants.getBankSMSList()!!

        Log.e("BankSMS","the list is $bankSMSList ")

        addBankSMSToRV()

        return binding.root
    }

    private fun addBankSMSToRV() {
        if(bankSMSList.isNotEmpty()) {

            binding.BankSMSRV.layoutManager = LinearLayoutManager(context)

            val adapter = MessageAdapter(bankSMSList,R.color.blue)

            binding.BankSMSRV.adapter = adapter

        }
    }

}