package com.example.webworks.neostore.ordermodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlacedOrderResponse{

    @SerializedName("status")
    @Expose
    val status:Int=0

    @SerializedName("message")
    @Expose
    val message: String? =null

    @SerializedName("user_msg")
    @Expose
    val userMsg:String?=null
}