package com.example.webworks.neostore.mycart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.cartmodel.ListCartModel

class MyCartAdapter(var context: Context, var cartData: ArrayList<ListCartModel>,
                    var accessToken: String) : RecyclerView.Adapter<MyCartAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.my_cart_items,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(cartData[position])

    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var txtOrderIdName:TextView = itemView.findViewById(R.id.orderName)
        var txtOrderNameDescription:TextView=itemView.findViewById(R.id.orderNameDescription)
        var txtOrderPrice=itemView.findViewById<TextView>(R.id.txtOrderPrice)
        var imgOrderId:ImageView=itemView.findViewById(R.id.imgOrder)
        var viewForeground:LinearLayout=itemView.findViewById(R.id.view_foreground)
        var viewBackground:LinearLayout=itemView.findViewById(R.id.view_background)



        fun bind(item:ListCartModel){
            txtOrderIdName.text=item.product!!.name
            txtOrderNameDescription.text=item.product.productCategory
            txtOrderPrice.text= "RS. ${item.product.cost}"
            Glide.with(context).load(item.product.productImages).into(imgOrderId)

            Log.e("productID", item.productId.toString())

        }
    }

        fun onItemRemove(position: Int){
            if (cartData.isNotEmpty())
            {
                if (cartData.size>=0)
                {

                    (context as MyCartActivity).deleteItemFromCart(accessToken, cartData[position].productId)
                    cartData.removeAt(position)
                }
            }
            else
                return
            notifyItemRemoved(position)
        }



}