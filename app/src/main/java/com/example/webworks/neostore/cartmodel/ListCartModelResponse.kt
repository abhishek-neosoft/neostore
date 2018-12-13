package com.example.webworks.neostore.cartmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListCartModelResponse {
    @SerializedName("status")
    @Expose
    val status: Int = 0
    @SerializedName("data")
    @Expose
    val data: List<ListCartModel>? = null
    @SerializedName("count")
    @Expose
    val count: Int = 0
    @SerializedName("total")
    @Expose
    val total: Int = 0
}