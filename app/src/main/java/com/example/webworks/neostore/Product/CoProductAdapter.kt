package com.example.webworks.neostore.Product

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.webworks.neostore.BR
import com.example.webworks.neostore.R
import com.example.webworks.neostore.databinding.ProductItemsBinding
import com.example.webworks.neostore.productmodel.ProductModel

class CoProductAdapter(var context: Context, val data: List<ProductModel>, var callBackAdapter: CallbackAdapter) : RecyclerView.Adapter<CoProductAdapter.CoProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoProductViewHolder {

        val productItemBInding: ProductItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.product_items, parent, false)
        return CoProductViewHolder(productItemBInding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CoProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class CoProductViewHolder(val mBinding: ProductItemsBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: ProductModel) {

            mBinding.setVariable(BR.result,item)

            itemView.setOnClickListener { callBackAdapter.adapterPosition(adapterPosition) }
           /* mBinding.txtTitle.text = item.name.toString()
            mBinding.txtDesp.text = item.producer.toString()
            mBinding.txtPrice.text = item.cost.toString()
            mBinding.ratingBar.rating = item.rating.toFloat()
            Glide.with(context).load(item.productImages).into(mBinding.imgProductLogo)*/
        }

    }

    interface CallbackAdapter {
        fun adapterPosition(position: Int)
    }
}

