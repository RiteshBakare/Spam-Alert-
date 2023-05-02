package com.gmail.bakareritesh1729.spamalert.TabLayout

import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.Adapter.MessageAdapter
import com.gmail.bakareritesh1729.spamalert.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {

    private lateinit var messageBinding: FragmentMessageBinding

    private lateinit var userSMSList : ArrayList<UserData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageBinding = FragmentMessageBinding.inflate(inflater, container, false)

        getUsersSMS()

        addDataToRecyclerView()

        messageBinding.btnCheckSpam.setOnClickListener {
            Toast.makeText(context,"ML Model to be added Soon !!! ",Toast.LENGTH_LONG).show()
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


        userSMSList = ArrayList()

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms._ID))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))

                userSMSList.add(UserData(id,address,date,body))
            }
        }

        if(userSMSList.isNotEmpty()) {
            Log.e("User SMS","id: ${userSMSList[0].id} \n")
            Log.e("User SMS","adders: ${userSMSList[0].address} \n")
            Log.e("User SMS","date: ${userSMSList[0].date} \n ")
            Log.e("User SMS","body: ${userSMSList[0].body} \n")

            Log.e("User SMS","id: ${userSMSList[1].id} \n")
            Log.e("User SMS","adders: ${userSMSList[1].address} \n")
            Log.e("User SMS","date: ${userSMSList[1].date} \n ")
            Log.e("User SMS","body: ${userSMSList[1].body} \n")

            Log.e("User SMS","id: ${userSMSList[2].id} \n")
            Log.e("User SMS","adders: ${userSMSList[2].address} \n")
            Log.e("User SMS","date: ${userSMSList[2].date} \n ")
            Log.e("User SMS","body: ${userSMSList[2].body} \n")

        }


        cursor?.close()
    }


    private fun addDataToRecyclerView() {
        if(userSMSList.isNotEmpty()) {
            val adapter = MessageAdapter(userSMSList)
            messageBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
            messageBinding.recyclerView.adapter = adapter
        }
    }

}