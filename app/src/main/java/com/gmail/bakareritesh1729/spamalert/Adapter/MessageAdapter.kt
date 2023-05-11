package com.gmail.bakareritesh1729.spamalert.Adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.R
import com.gmail.bakareritesh1729.spamalert.databinding.ItemEachMessageBinding

class MessageAdapter(private val dataList: ArrayList<UserData>, private val cardColor: Int) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    class ViewHolder(itemView: ItemEachMessageBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val address = itemView.tvAddress
        val body = itemView.tvBody
        private val cardView = itemView.MessageCardView


        init {
            cardView.setOnClickListener {

                val context = itemView.root.context

                val dialog = Dialog(context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.message_dialog)

                val addressTextView = dialog.findViewById<TextView>(R.id.addressTextView)
                val bodyTextView = dialog.findViewById<TextView>(R.id.bodyTextView)

                addressTextView.text = address.text.toString()
                bodyTextView.text = body.text.toString()

                dialog.show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEachMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val userSMS = dataList[position]

        holder.address.text = userSMS.address.toString()
        holder.body.text = userSMS.body.toString()

//        holder.cardView.setCardBackgroundColor(cardColor)

    }


}