package com.example.webworks.neostore.ordermodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class OrderListModelResponse{

    @SerializedName("status")
    @Expose
    val status: Int? = null
    @SerializedName("data")
    @Expose
    val data: List<OrderListModel>? = null
    @SerializedName("message")
    @Expose
    val message: String? = null
    @SerializedName("user_msg")
    @Expose
    val userMsg: String? = null
}