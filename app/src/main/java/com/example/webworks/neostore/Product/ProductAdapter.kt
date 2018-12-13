package com.example.webworks.neostore.Product

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.description.ProductDescriptionActivity
import com.example.webworks.neostore.productmodel.ProductModel

class ProductAdapter(var data: List<ProductModel>, var context: Context, var catagory: String) : RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_items, parent, false)
        return RecyclerViewHolder(view,context)
    }
    override fun getItemCount(): Int {

        return data.size
    }
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(data[position])
    }
    inner class RecyclerViewHolder(itemView: View?, var context: Context?) : RecyclerView.ViewHolder(itemView) {

        var productLogo = itemView!!.findViewById<ImageView>(R.id.img_product_logo)
        var name = itemView?.findViewById<TextView>(R.id.txt_title)
        var producer = itemView?.findViewById<TextView>(R.id.txt_desp)
        var cost = itemView?.findViewById<TextView>(R.id.txt_price)
        val rating = itemView!!.findViewById<RatingBar>(R.id.ratingBar)

        fun bind(item: ProductModel) {

            name!!.text = item.name
            producer!!.text = item.producer
            rating!!.rating = item.rating.toFloat()
            cost!!.text = "${item.cost}"

            itemView.setOnClickListener {
                context!!.startActivity(Intent(context,ProductDescriptionActivity::class.java)
                        .putExtra("product_id",item.id)
                        .putExtra("catagoryName",catagory))
            }

            Glide.with(context).load(item.productImages).into(productLogo)
        }
    }
}