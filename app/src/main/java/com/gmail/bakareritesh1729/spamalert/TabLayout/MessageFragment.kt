package com.gmail.bakareritesh1729.spamalert.TabLayout

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.Adapter.MessageAdapter
import com.gmail.bakareritesh1729.spamalert.CheckSpamActivity
import com.gmail.bakareritesh1729.spamalert.Model.Constants
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {

    private lateinit var messageBinding: FragmentMessageBinding

    private  var userSMSList: ArrayList<UserData> = ArrayList()


    private var smsList : ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageBinding = FragmentMessageBinding.inflate(inflater, container, false)

        getUsersSMS()

        addDataToRecyclerView()

        messageBinding.btnCheckSpam.setOnClickListener {
            Constants.setUserSMS(userSMSList)
            startActivity(Intent(requireActivity(), CheckSpamActivity::class.java))
        }

        return messageBinding.root

    }

    override fun onStart() {
        super.onStart()
        getUsersSMS()
    }

    private fun getUsersSMS() {
        val contentResolver = requireContext().contentResolver

        val projection = arrayOf(
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.DATE,
            Telephony.Sms.BODY
        )

        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            projection,
            null,
            null,
            Telephony.Sms.DEFAULT_SORT_ORDER
        )



        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms._ID))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))

                userSMSList.add(UserData(id, address, date, body))

                smsList.add(body.toString())
            }
        }


        cursor?.close()
    }


    private fun addDataToRecyclerView() {
        if (userSMSList.isNotEmpty()) {
            val adapter = MessageAdapter(userSMSList, R.color.light_blue)
            messageBinding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext().applicationContext)
            messageBinding.recyclerView.adapter = adapter
        }
    }

}