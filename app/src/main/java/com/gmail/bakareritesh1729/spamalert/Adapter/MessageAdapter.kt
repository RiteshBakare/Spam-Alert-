package com.gmail.bakareritesh1729.spamalert.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.databinding.ItemEachMessageBinding

class MessageAdapter(private val dataList: ArrayList<UserData>,private val cardColor : Int) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    class ViewHolder(itemView: ItemEachMessageBinding) :
        RecyclerView.ViewHolder(itemView.root) {
            val address = itemView.tvAddress
            val body = itemView.tvBody
            val cardView = itemView.MessageCardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEachMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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