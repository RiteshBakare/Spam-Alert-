package com.gmail.bakareritesh1729.spamalert.SMSResult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.bakareritesh1729.spamalert.Constants
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentSpamSMSBinding


class SpamSMSFragment : Fragment() {

    private lateinit var binding: FragmentSpamSMSBinding

    private lateinit var spamSMSList :ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpamSMSBinding.inflate(layoutInflater,container,false)
        spamSMSList = Constants.getSpamSMSList()!!



        return binding.root
    }


}