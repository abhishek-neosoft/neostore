package com.example.webworks.neostore.addaddress

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.sqldatabase.SqlDatabaseManage

class AddListAdapter(var context: Context, var address: ArrayList<String>, accessToken: String) : RecyclerView.Adapter<AddListAdapter.RecyclerViewHolder>() {

    var callBackAdapterAddress: CallBackAdapterAddress? = null

    init {
        callBackAdapterAddress = context as CallBackAdapterAddress
    }

    var positionAddressData: String? = null
    var lastCheckedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.address_list_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return address.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.message.text = address[position]

        holder.rb.isChecked = position == lastCheckedPosition
    }

    inner class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var rb: RadioButton = itemView!!.findViewById(R.id.rd_order_radio_btn)
        var message: TextView = itemView!!.findViewById(R.id.txt_saved_add)
        var i = rb.setOnClickListener {

            positionAddressData = address[adapterPosition]
            callBackAdapterAddress!!.onClickPositionAddress(positionAddressData!!)
            lastCheckedPosition = adapterPosition
            notifyDataSetChanged()
        }

        var long=itemView!!.setOnLongClickListener {
            val alertDialog=AlertDialog.Builder(context)
            alertDialog.setTitle("Do you want to delete").setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->

                val sqlDatabaseManage=SqlDatabaseManage(context)
                if (sqlDatabaseManage.deleteData(adapterPosition)==true)
                {

                    address.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)


                    Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }).create().show()

            return@setOnLongClickListener true
        }
    }

    interface CallBackAdapterAddress {
        fun onClickPositionAddress(positionAddress: String)
    }

}
