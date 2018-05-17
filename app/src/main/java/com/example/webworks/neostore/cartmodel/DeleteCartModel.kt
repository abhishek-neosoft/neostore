package com.example.webworks.neostore.cartmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DeleteCartModel {

    @SerializedName("status")
    @Expose
    private val status: Int? = null
    @SerializedName("data")
    @Expose
    private val data: Boolean? = null
    @SerializedName("total_carts")
    @Expose
    private val totalCarts: Int? = null
    @SerializedName("message")
    @Expose
    private val message: String? = null
    @SerializedName("user_msg")
    @Expose
    private val userMsg: String? = null
}