package com.example.webworks.neostore.myorder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.webworks.neostore.R
import com.example.webworks.neostore.ordermodel.OrderListModel


class MyOrderAdapter(var data: List<OrderListModel>?) : RecyclerView.Adapter<MyOrderAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.my_order_list,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  data!!.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.orderId.text= data!![position].id.toString()
        holder.orderCost.text= data!![position].cost.toString()
        holder.orderId.text= data!![position].created.toString()
    }

    inner class RecyclerViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView)
    {
        var orderId = itemView!!.findViewById<TextView>(R.id.txt_order_id)
        var orderCost:TextView=itemView!!.findViewById(R.id.txt_order_cost)
        var orderDesp:TextView=itemView!!.findViewById(R.id.txt_order_decp)

    }
}