package com.example.webworks.neostore.cartmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListCartModel{

    @SerializedName("id")
    @Expose
    val  id: Int =0
    @SerializedName("product_id")
    @Expose
    val  productId: Int =0
    @SerializedName("quantity")
    @Expose
    val quantity:Int =0
    @SerializedName("product")
    @Expose
    val  product:CartProductResponse? =null
}