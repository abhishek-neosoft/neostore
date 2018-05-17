package com.example.webworks.neostore.addaddress

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.example.webworks.neostore.R

class AddListAdapter(val allAddress: ArrayList<String>) : RecyclerView.Adapter<AddListAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        var inflater=LayoutInflater.from(parent.context)
        var view:View = inflater.inflate(R.layout.address_list_activity,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allAddress.size

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var datas = allAddress[position]
        holder.rb.isSelected
        holder.message.text =datas
    }


    inner class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var rb:RadioButton= itemView!!.findViewById(R.id.rd_order_radio_btn)
        var message:TextView = itemView!!.findViewById(R.id.txt_saved_add)

    }
}
