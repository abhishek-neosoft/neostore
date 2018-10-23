package com.example.webworks.neostore.description

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseImages

class ProductAdapterActivity(var context: Context, var data: List<DescriptionResponseImages>) : RecyclerView.Adapter<ProductAdapterActivity.RecyclerViewHolder>()
{
    var raw_index:Int=-1
    var callbackAdapterPosition : CallbackAdapterPosition? = null
    init {
        callbackAdapterPosition = context as CallbackAdapterPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.description_image_items, parent, false)
        return RecyclerViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class RecyclerViewHolder(itemView: View?, var context: Context?) : RecyclerView.ViewHolder(itemView) {
            var image = itemView!!.findViewById<ImageView>(R.id.recycler_image)

        fun bind(item: DescriptionResponseImages) {

            Glide.with(context).load(item.image).into(image)
            image.setOnClickListener {
                callbackAdapterPosition!!.onClickPosition(adapterPosition)
                raw_index = adapterPosition
                notifyDataSetChanged()
            }

            if (raw_index == adapterPosition) {
                image.background = context!!.resources.getDrawable(R.drawable.product_description_img_edit)
            }
            else {
                image.background = null
            }
        }
    }

    interface CallbackAdapterPosition
    {
        fun onClickPosition(position:Int)
    }
}