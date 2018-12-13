package com.example.webworks.neostore.cartmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddToCartModelResponse{

    @SerializedName("status")
    @Expose
    val  status: Int? =null
    @SerializedName("data")
    @Expose
    val data: Boolean? = null
    @SerializedName("total_carts")
    @Expose
    val totalCarts: Integer? =null
    @SerializedName("message")
    @Expose
    val message:String?=null
    @SerializedName("user_msg")
    @Expose
    val userMsg:String?=null
}